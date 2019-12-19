package top.thttnt.serviceoutsourcing.oss.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import top.thttnt.serviceoutsourcing.common.dto.server.ServerUploadFileSuccess
import top.thttnt.serviceoutsourcing.common.type.ErrorType
import top.thttnt.serviceoutsourcing.common.type.FileType
import top.thttnt.serviceoutsourcing.common.type.getException
import top.thttnt.serviceoutsourcing.oss.service.FileService
import javax.annotation.Resource
import javax.xml.ws.Service

@RestController
class FileController {

    @Resource
    lateinit var fileService: FileService

    @RequestMapping("upload")
    fun upload(@RequestParam file: MultipartFile, @RequestParam type: Int): ResponseEntity<ServerUploadFileSuccess> {
        when (FileType.fromId(type)) {
            FileType.UNKNOWN -> throw ErrorType.UNSUPPORTED_FILE.getException()
            else -> {
                val uuid = fileService.uploadFile(FileType.fromId(type), file)
                return ResponseEntity.ok(ServerUploadFileSuccess(uuid = uuid))
            }
        }
    }
}