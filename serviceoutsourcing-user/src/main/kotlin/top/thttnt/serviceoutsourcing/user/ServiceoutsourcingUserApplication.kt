package top.thttnt.serviceoutsourcing.user

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication(scanBasePackages = ["top.thttnt.serviceoutsourcing"])
@EntityScan(basePackages = ["top.thttnt.serviceoutsourcing"])
@EnableFeignClients(basePackages = ["top.thttnt.serviceoutsourcing"])
class ServiceoutsourcingUserApplication

fun main(args: Array<String>) {
    runApplication<ServiceoutsourcingUserApplication>(*args)
}
