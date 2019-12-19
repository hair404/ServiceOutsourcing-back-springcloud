package top.thttnt.serviceoutsourcing.payment.service

import org.springframework.stereotype.Service
import top.thttnt.serviceoutsourcing.common.dto.server.ServerPayInfo
import top.thttnt.serviceoutsourcing.common.model.Payment
import top.thttnt.serviceoutsourcing.common.type.PaymentState
import top.thttnt.serviceoutsourcing.common.utils.alipay.AlipayTools
import top.thttnt.serviceoutsourcing.common.utils.alipay.PayInfo
import top.thttnt.serviceoutsourcing.payment.repository.PaymentRepository
import java.util.*
import javax.annotation.Resource

@Service
class PayService {

    @Resource
    private lateinit var paymentRepository: PaymentRepository

    fun pay(amount: Int, description: String): ServerPayInfo {
        //随机生成OrderId
        val orderId = UUID.randomUUID().toString().replace("-", "")

        val payment = Payment().apply {
            this.amount = amount
            this.orderId = orderId
            this.description = description
            this.state = PaymentState.PENDING.id
        }

        //保存数据
        paymentRepository.save(payment)

        return ServerPayInfo(
                paymentId = payment.id,
                payForm = AlipayTools.pay(PayInfo().apply {
                    this.outTradeNo = orderId
                    this.totalAmount = amount.toDouble()
                    this.subject = description
                }, "", ""))
    }
}
