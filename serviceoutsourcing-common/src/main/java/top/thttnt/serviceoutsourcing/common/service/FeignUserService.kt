package top.thttnt.serviceoutsourcing.common.service

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import top.thttnt.serviceoutsourcing.common.configuration.FeignExceptionConfiguration
import top.thttnt.serviceoutsourcing.common.dto.server.ServerUserExist
import top.thttnt.serviceoutsourcing.common.dto.server.ServerUserIdentity

@FeignClient(value = "user", configuration = [FeignExceptionConfiguration::class])
interface FeignUserService {

    @RequestMapping("identity", method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun identity(@RequestParam token: String): ServerUserIdentity

    @RequestMapping("exist", method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun exist(@RequestParam uid: Int): ServerUserExist
}