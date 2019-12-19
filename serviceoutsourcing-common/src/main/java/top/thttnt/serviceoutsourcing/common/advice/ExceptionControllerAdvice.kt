package top.thttnt.serviceoutsourcing.common.advice

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import top.thttnt.serviceoutsourcing.common.exception.RuntimeException
import top.thttnt.serviceoutsourcing.common.response.ResultBean

@ControllerAdvice
class ExceptionControllerAdvice {

    @ExceptionHandler(RuntimeException::class)
    fun exceptionHandler(exception: RuntimeException): ResponseEntity<ResultBean> {
        return exception.getResponseBody()
    }
}