package top.thttnt.serviceoutsourcing.project.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import top.thttnt.serviceoutsourcing.common.annotation.Authorization
import top.thttnt.serviceoutsourcing.common.type.UserType
import top.thttnt.serviceoutsourcing.project.service.ProjectPayService
import javax.annotation.Resource

@RequestMapping("pay")
@RestController
class PayController {

    @Resource
    lateinit var projectPayService: ProjectPayService

    @RequestMapping("companyDeposit")
    @Authorization(types = [UserType.COMPANY])
    fun payCompanyDeposit(@RequestParam projectId : Int, @RequestAttribute uid: Int): ResponseEntity<String> {
        return ResponseEntity.ok(projectPayService.payCompanyDeposit(uid,projectId))
    }
}