package top.thttnt.serviceoutsourcing.common.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import top.thttnt.serviceoutsourcing.common.interceptor.HeaderInterceptor

@Configuration
open class HeadersConfiguration{

    @Bean
    open fun getRequestInterceptor() : HeaderInterceptor{
        return HeaderInterceptor()
    }
}