package top.thttnt.serviceoutsourcing.common.configuration

import com.alibaba.fastjson.JSON
import com.netflix.hystrix.exception.HystrixBadRequestException
import feign.Response
import feign.Util
import feign.codec.ErrorDecoder
import org.springframework.context.annotation.Bean
import top.thttnt.serviceoutsourcing.common.response.ResultBean
import top.thttnt.serviceoutsourcing.common.type.ErrorType
import top.thttnt.serviceoutsourcing.common.type.getException
import java.lang.Exception

class FeignExceptionConfiguration {

    @Bean
    fun errorDecoder() : ErrorDecoder{
        return RuntimeErrorDecoder()
    }

    class RuntimeErrorDecoder : ErrorDecoder {

        override fun decode(key: String, response: Response): HystrixBadRequestException {
            if (response.status() >= 500) {
                return HystrixBadRequestException(Util.toString(response.body().asReader()))
            }
            println(Util.toString(response.body().asReader()))
            val resultBean = JSON.parseObject(Util.toString(response.body().asReader()), ResultBean::class.java)
            return ErrorType.fromCode(resultBean.code).getException()
        }

    }
}