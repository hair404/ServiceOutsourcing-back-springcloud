package top.thttnt.serviceoutsourcing.common.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter
import top.thttnt.serviceoutsourcing.common.interceptor.AuthInterceptor
import javax.annotation.Resource

@Configuration
open class AuthorizationConfiguration : WebMvcConfigurationSupport() {

    @Resource
    lateinit var authInterceptor: AuthInterceptor

    override fun addInterceptors(registry: InterceptorRegistry) {
        super.addInterceptors(registry)
        registry.addInterceptor(authInterceptor).addPathPatterns("/**")
    }

}