package top.thttnt.serviceoutsourcing.common.exception

import com.netflix.hystrix.exception.HystrixBadRequestException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import top.thttnt.serviceoutsourcing.common.response.ResultBean
import top.thttnt.serviceoutsourcing.common.type.ErrorType

class RuntimeException(private val error: ErrorType) : HystrixBadRequestException(error.description) {

    fun getResponseBody(): ResponseEntity<ResultBean> {
        return ResponseEntity<ResultBean>(ResultBean.fromErrorType(error), error.status)
    }
}