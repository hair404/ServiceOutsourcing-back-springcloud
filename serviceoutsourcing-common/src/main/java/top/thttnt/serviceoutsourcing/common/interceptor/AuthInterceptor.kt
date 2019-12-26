package top.thttnt.serviceoutsourcing.common.interceptor

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import top.thttnt.serviceoutsourcing.common.annotation.Authorization
import top.thttnt.serviceoutsourcing.common.service.FeignUserService
import top.thttnt.serviceoutsourcing.common.type.ErrorType
import top.thttnt.serviceoutsourcing.common.type.UserType
import top.thttnt.serviceoutsourcing.common.type.getException
import javax.annotation.Resource
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthInterceptor : HandlerInterceptor {

    @Lazy
    @Resource
    lateinit var feignUserService: FeignUserService

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod) {
            return true
        }
        val authorization = handler.getMethodAnnotation(Authorization::class.java) ?: return true
        val token = request.getHeader("Authorization") ?: throw ErrorType.INVALID_TOKEN.getException()
        val identification = feignUserService.identity(token)
        if (authorization.types.contains(UserType.fromId(identification.type))) {
            request.setAttribute("uid", identification.uid)
            return true
        } else {
            throw ErrorType.PERMISSION_DENIED.getException()
        }
    }
}