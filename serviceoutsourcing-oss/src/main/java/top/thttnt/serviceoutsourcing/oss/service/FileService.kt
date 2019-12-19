package top.thttnt.serviceoutsourcing.oss.service

import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import top.thttnt.serviceoutsourcing.common.model.FileInfo
import top.thttnt.serviceoutsourcing.common.type.FileType
import top.thttnt.serviceoutsourcing.common.utils.OSSTools
import top.thttnt.serviceoutsourcing.oss.repository.FileInfoRepository
import java.util.*
import javax.annotation.Resource

@Service
class FileService {

    @Resource
    private lateinit var fileInfoRepository: FileInfoRepository

    fun uploadFile(type: FileType, file: MultipartFile): String {
        //生成uuid
        val uuid = UUID.randomUUID().toString().replace("-","")
        //生成保存路径
        val path = type.path + uuid

        val fileInfo = FileInfo().apply {
            this.filename = file.originalFilename ?: "unknown"
            this.type = type.id
            this.uuid == uuid
        }

        //保存数据
        fileInfoRepository.save(fileInfo)
        OSSTools.uploadFile(path,file.inputStream.readBytes())

        return uuid
    }
}