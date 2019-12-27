package top.thttnt.serviceoutsourcing.common.service

import feign.Headers
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import top.thttnt.serviceoutsourcing.common.configuration.FeignExceptionConfiguration
import top.thttnt.serviceoutsourcing.common.configuration.FeignMultipartFileSupport
import top.thttnt.serviceoutsourcing.common.dto.server.ServerUploadFileSuccess

@FeignClient(name = "file", configuration = [FeignExceptionConfiguration::class, FeignMultipartFileSupport::class])
interface FeignFileService {

    @PostMapping("upload", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun upload(@RequestParam type: Int, @RequestPart file: MultipartFile): ServerUploadFileSuccess
}