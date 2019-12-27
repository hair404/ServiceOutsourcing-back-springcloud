package top.thttnt.serviceoutsourcing.oss.handler

import top.thttnt.serviceoutsourcing.common.type.FileType

interface FileHandler {

    companion object {
        private val handlers = HashMap<FileType, FileHandler>()

        fun handle(type: FileType, data: ByteArray): ByteArray {
            return handlers[type]?.handle(data) ?: data
        }

        init {
            handlers[FileType.PROJECT_IMAGE] = ProjectImageHandler()
        }
    }

    fun handle(data: ByteArray): ByteArray
}