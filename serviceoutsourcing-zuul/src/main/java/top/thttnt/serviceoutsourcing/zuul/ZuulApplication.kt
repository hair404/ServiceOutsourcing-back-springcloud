package top.thttnt.serviceoutsourcing.zuul

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.cloud.netflix.zuul.EnableZuulProxy

@EnableEurekaClient
@EnableZuulProxy
@SpringBootApplication
open class ZuulApplication

fun main(args: Array<String>) {
    runApplication<ZuulApplication>(*args)
}