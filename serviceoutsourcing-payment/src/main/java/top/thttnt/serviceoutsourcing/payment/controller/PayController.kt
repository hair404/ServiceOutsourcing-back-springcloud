package top.thttnt.serviceoutsourcing.payment.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import top.thttnt.serviceoutsourcing.common.dto.server.ServerPayInfo
import top.thttnt.serviceoutsourcing.payment.service.PayService
import javax.annotation.Resource

@RestController
class PayController {

    @Resource
    lateinit var payService : PayService

    @RequestMapping("pay")
    fun pay(@RequestParam amount: Int, @RequestParam description: String): ResponseEntity<ServerPayInfo> {
        return ResponseEntity.ok().body(payService.pay(amount,description))
    }


}