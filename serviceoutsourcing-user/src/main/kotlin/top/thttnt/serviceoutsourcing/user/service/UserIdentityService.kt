package top.thttnt.serviceoutsourcing.user.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils
import top.thttnt.serviceoutsourcing.common.dto.server.ServerLoginSuccess
import top.thttnt.serviceoutsourcing.common.dto.server.ServerUserIdentity
import top.thttnt.serviceoutsourcing.common.model.UserIdentity
import top.thttnt.serviceoutsourcing.common.type.ErrorType
import top.thttnt.serviceoutsourcing.common.type.getException
import top.thttnt.serviceoutsourcing.user.repository.UserIdentifyRepository
import top.thttnt.serviceoutsourcing.user.repository.UserRepository
import java.util.*
import javax.annotation.Resource

@Service
class UserIdentityService {

    @Resource
    private lateinit var userRepository: UserRepository

    @Resource
    private lateinit var userIdentityRepository: UserIdentifyRepository

    @Resource
    private lateinit var codeService: CodeService

    fun login(telephone: String, password: String, loginId: String, code: String): ServerLoginSuccess {
        val user = userRepository.findByTelephone(telephone) ?: throw ErrorType.USER_NON_EXISTS.getException()
        //校验登陆验证码
        if (!codeService.checkLoginVerificationCode(loginId, code)) throw ErrorType.WRONG_LOGIN_VERIFICATION_CODE.getException()
        //校验密码
        if (user.password != DigestUtils.md5DigestAsHex((DigestUtils.md5DigestAsHex(password.toByteArray()) + telephone).toByteArray())) throw ErrorType.WRONG_PASSWORD.getException()

        //生成身份信息
        val token = UUID.randomUUID().toString()
        val userIdentity = UserIdentity().apply {
            this.lastTime = System.currentTimeMillis()
            this.token = token
            this.uid = user.id
        }
        userIdentityRepository.deleteById(user.id)
        userIdentityRepository.save(userIdentity)

        return ServerLoginSuccess(
                        id = user.id,
                        token = token
        )
    }

    fun getIdentification(token: String): ServerUserIdentity {
        val identity = userIdentityRepository.findByToken(token) ?: throw ErrorType.INVALID_TOKEN.getException()
        val user = userRepository.findByIdOrNull(identity.uid) ?: throw ErrorType.USER_NON_EXISTS.getException()
        return ServerUserIdentity(
                uid = user.id,
                type = user.type
        )
    }



}