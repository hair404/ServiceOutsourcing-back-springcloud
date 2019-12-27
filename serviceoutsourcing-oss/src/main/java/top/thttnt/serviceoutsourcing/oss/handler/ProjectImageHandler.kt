package top.thttnt.serviceoutsourcing.oss.handler

import net.coobird.thumbnailator.Thumbnails
import java.io.ByteArrayOutputStream

class ProjectImageHandler : FileHandler {

    override fun handle(data: ByteArray): ByteArray {
        val bos = ByteArrayOutputStream()
        Thumbnails.of(data.inputStream()).outputFormat("jpg").forceSize(512, 512)
                .toOutputStream(bos)
        return bos.toByteArray()
    }

}