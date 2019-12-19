package top.thttnt.serviceoutsourcing.payment

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication(scanBasePackages = ["top.thttnt.serviceoutsourcing"])
@EntityScan(basePackages = ["top.thttnt.serviceoutsourcing"])
@EnableFeignClients(basePackages = ["top.thttnt.serviceoutsourcing"])
open class PaymentApplication

fun main(args: Array<String>) {
    runApplication<PaymentApplication>(*args)
}