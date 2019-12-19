package top.thttnt.serviceoutsourcing.project

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication(scanBasePackages = ["top.thttnt.serviceoutsourcing"])
@EntityScan(basePackages = ["top.thttnt.serviceoutsourcing"])
@EnableFeignClients(basePackages = ["top.thttnt.serviceoutsourcing"])
open class ProjectApplication

fun main(args: Array<String>) {
    runApplication<ProjectApplication>(*args)
}