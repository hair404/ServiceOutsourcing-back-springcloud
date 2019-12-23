package top.thttnt.serviceoutsourcing.payment.dp

import org.springframework.stereotype.Component
import top.thttnt.serviceoutsourcing.common.dto.server.ServerPayment
import top.thttnt.serviceoutsourcing.common.type.ErrorType
import top.thttnt.serviceoutsourcing.common.type.getException
import top.thttnt.serviceoutsourcing.payment.repository.PaymentRepository
import top.thttnt.serviceoutsourcing.payment.service.PayService
import javax.annotation.Resource

@Component
class PaymentDataProcessor {

    @Resource
    private lateinit var payService: PayService

    fun getPayment(paymentId : Int): ServerPayment {
        val payment = payService.getPayment(paymentId)
        return ServerPayment(
                id = payment.id,
                state = payment.state,
                description = payment.description,
                orderId = payment.orderId,
                amount = payment.amount
        )
    }


}