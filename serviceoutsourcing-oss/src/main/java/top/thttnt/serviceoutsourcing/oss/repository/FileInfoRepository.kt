package top.thttnt.serviceoutsourcing.oss.repository

import org.springframework.data.repository.CrudRepository
import top.thttnt.serviceoutsourcing.common.model.FileInfo

interface FileInfoRepository : CrudRepository<FileInfo, Int> {

    fun findByUuid(uuid: String): FileInfo?

}