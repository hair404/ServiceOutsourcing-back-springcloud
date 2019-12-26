package top.thttnt.serviceoutsourcing.admin.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import top.thttnt.serviceoutsourcing.admin.service.ConfigService
import javax.annotation.Resource

@RestController
@RequestMapping("config")
class ConfigController{

    @Resource
    lateinit var configService: ConfigService

    @RequestMapping("category")
    fun category(): List<ConfigService.CategoryParent> {
        return configService.getCategory()
    }
}