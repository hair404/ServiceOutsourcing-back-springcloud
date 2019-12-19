package top.thttnt.serviceoutsourcing.user.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import top.thttnt.serviceoutsourcing.common.annotation.Authorization
import top.thttnt.serviceoutsourcing.common.dto.server.*
import top.thttnt.serviceoutsourcing.common.response.ResultBean
import top.thttnt.serviceoutsourcing.common.type.UserType
import top.thttnt.serviceoutsourcing.user.dto.CodeDataProcessor
import top.thttnt.serviceoutsourcing.user.dto.UserDataProcessor
import top.thttnt.serviceoutsourcing.user.service.CodeService
import top.thttnt.serviceoutsourcing.user.service.UserIdentityService
import top.thttnt.serviceoutsourcing.user.service.UserService
import javax.annotation.Resource
import javax.servlet.http.HttpServletResponse

@RestController
class UserController {

    @Resource
    private lateinit var userService: UserService

    @Resource
    private lateinit var codeService: CodeService

    @Resource
    private lateinit var userIdentityService: UserIdentityService

    @Resource
    private lateinit var codeDataProcessor: CodeDataProcessor

    @Resource
    private lateinit var userDataProcessor: UserDataProcessor

    @PostMapping("register")
    fun register(@RequestParam telephone: String, @RequestParam password: String,
                 @RequestParam code: String, @RequestParam email: String,
                 @RequestParam nickname: String, @RequestParam realname: String,
                 @RequestParam type: Int): ResponseEntity<Any> {
        userService.register(
                telephone = telephone,
                password = password,
                code = code,
                email = email,
                nickname = nickname,
                realname = realname,
                type = type
        )
        return ResponseEntity.noContent().build<Any>()
    }

    @RequestMapping("sendTelephoneCode")
    fun sendTelephoneCode(@RequestParam telephone: String): ResponseEntity<Any> {
        codeService.sendTelephoneVerificationCode(telephone = telephone)
        return ResponseEntity.noContent().build<Any>()
    }

    @RequestMapping("login")
    fun login(@RequestParam telephone: String, @RequestParam password: String, @RequestParam loginId: String, @RequestParam code: String): ResponseEntity<ServerLoginSuccess> {
        return ResponseEntity.ok(userIdentityService.login(telephone, password, loginId, code))
    }

    @RequestMapping("info")
    @Authorization(types = [UserType.STUDIO, UserType.COMPANY])
    fun info(@RequestAttribute uid: Int): ResponseEntity<ServerUser> {
        return ResponseEntity.ok(userDataProcessor.getUserInfo(uid))
    }

    @RequestMapping("identity")
    fun identity(@RequestParam token: String): ResponseEntity<ServerUserIdentity> {
        return ResponseEntity.ok().body(userIdentityService.getIdentification(token))
    }

    @RequestMapping("exist")
    fun exist(@RequestParam uid: Int): ResponseEntity<ServerUserExist> {
        return ResponseEntity.ok().body(userService.exist(uid))
    }

    @RequestMapping("code")
    fun code(response: HttpServletResponse): ResponseEntity<ServerLoginCode> {
        return ResponseEntity.ok(codeDataProcessor.getLoginVerificationCode())
    }
}