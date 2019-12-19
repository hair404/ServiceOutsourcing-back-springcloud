package top.thttnt.serviceoutsourcing.common.service

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.multipart.MultipartFile
import top.thttnt.serviceoutsourcing.common.configuration.FeignExceptionConfiguration
import top.thttnt.serviceoutsourcing.common.dto.server.ServerUploadFileSuccess

@FeignClient(name = "file", configuration = [FeignExceptionConfiguration::class])
interface FeignFileService {

    @RequestMapping("upload",method = [RequestMethod.POST])
    fun upload(@RequestParam type: Int, @RequestPart file: MultipartFile) : ServerUploadFileSuccess
}