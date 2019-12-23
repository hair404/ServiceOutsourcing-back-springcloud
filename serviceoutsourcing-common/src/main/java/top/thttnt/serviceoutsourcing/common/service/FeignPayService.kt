package top.thttnt.serviceoutsourcing.common.service

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import top.thttnt.serviceoutsourcing.common.configuration.FeignExceptionConfiguration
import top.thttnt.serviceoutsourcing.common.dto.server.ServerPayInfo
import top.thttnt.serviceoutsourcing.common.dto.server.ServerPayment

@FeignClient(name = "pay", configuration = [FeignExceptionConfiguration::class])
interface FeignPayService {

    @PostMapping("pay")
    fun pay(@RequestParam amount: Int, @RequestParam description: String): ServerPayInfo

    @PostMapping("getPayment")
    fun getPayment(@RequestParam paymentId: Int) : ServerPayment
}