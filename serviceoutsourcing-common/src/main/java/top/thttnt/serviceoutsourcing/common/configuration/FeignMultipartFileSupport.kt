package top.thttnt.serviceoutsourcing.common.configuration

import feign.Logger
import feign.codec.Encoder
import feign.form.spring.SpringFormEncoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.Scope

@Configuration
public open class FeignMultipartFileSupport {

    @Bean
    @Primary
    @Scope("prototype")
    open fun feignFormEncoder(): Encoder {
        return SpringFormEncoder()
    }

    @Bean
    open fun multipartLoggerLevel(): Logger.Level {
        return Logger.Level.FULL
    }
}