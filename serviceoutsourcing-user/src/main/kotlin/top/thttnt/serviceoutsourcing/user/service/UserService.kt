package top.thttnt.serviceoutsourcing.user.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.util.DigestUtils
import top.thttnt.serviceoutsourcing.common.dto.server.ServerUserExist
import top.thttnt.serviceoutsourcing.common.model.User
import top.thttnt.serviceoutsourcing.common.type.ErrorType
import top.thttnt.serviceoutsourcing.common.type.UserType
import top.thttnt.serviceoutsourcing.common.type.getException
import top.thttnt.serviceoutsourcing.common.utils.toIntArray
import top.thttnt.serviceoutsourcing.user.repository.UserRepository
import top.thttnt.serviceoutsourcing.user.repository.UserTagRepository
import javax.annotation.Resource

@Service
class UserService {

    @Resource
    private lateinit var userRepository: UserRepository

    @Resource
    private lateinit var userTagRepository: UserTagRepository

    @Resource
    private lateinit var codeService: CodeService

    /**
     * 注册
     * @param code 手机验证码
     */
    fun register(telephone: String, password: String, nickname: String, realname: String, code: String, email: String, type: Int) {
        //校验手机验证码
        if (!codeService.checkTelephoneVerificationCode(telephone, code)) throw ErrorType.WRONG_TELEPHONE_VERIFICATION_CODE.getException()
        //查重
        if (userRepository.findByTelephone(telephone) != null) throw ErrorType.USER_EXISTS.getException()
        //判断注册类型是否合法
        if (type != UserType.STUDIO.id && type != UserType.COMPANY.id) throw ErrorType.REGISTER_NOT_ALLOWED.getException()

        val user = User().apply {
            this.telephone = telephone
            this.email = email
            this.password = DigestUtils.md5DigestAsHex((DigestUtils.md5DigestAsHex(password.toByteArray()) + telephone).toByteArray())
            this.nickname = nickname
            this.realname = realname
            this.type = type
        }

        userRepository.save(user)
    }

    fun getUser(uid: Int): User {
        return userRepository.findByIdOrNull(uid) ?: throw ErrorType.USER_NON_EXISTS.getException()
    }

    fun exist(uid: Int): ServerUserExist {
        val user = getUser(uid)
        return ServerUserExist(uid = user.id, type = user.type)
    }

    fun getTagList(uid: Int): Array<Int> {
        val userTag = userTagRepository.findByIdOrNull(uid) ?: return arrayOf()
        return userTag.tags.toIntArray()
    }


}