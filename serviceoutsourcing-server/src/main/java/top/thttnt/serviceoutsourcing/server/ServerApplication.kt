package top.thttnt.serviceoutsourcing.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
open class ServerApplication

fun main(args : Array<String>) {
    runApplication<ServerApplication>(*args)
}