package top.thttnt.serviceoutsourcing.common.service

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.PostMapping
import top.thttnt.serviceoutsourcing.common.configuration.FeignExceptionConfiguration
import top.thttnt.serviceoutsourcing.common.dto.server.ServerUserExist
import top.thttnt.serviceoutsourcing.common.dto.server.ServerUserIdentity

@FeignClient(value = "user", configuration = [FeignExceptionConfiguration::class])
interface FeignUserService {

    @PostMapping("identity")
    fun identity(token: String): ServerUserIdentity

    @PostMapping("exist")
    fun exist(uid: Int): ServerUserExist
}