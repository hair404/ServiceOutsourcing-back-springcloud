package top.thttnt.serviceoutsourcing.user.dto

import org.springframework.stereotype.Component
import sun.misc.BASE64Encoder
import top.thttnt.serviceoutsourcing.common.dto.server.ServerLoginCode
import top.thttnt.serviceoutsourcing.user.service.CodeService
import java.io.ByteArrayOutputStream
import java.util.*
import javax.annotation.Resource
import javax.imageio.ImageIO

@Component
class CodeDataProcessor {

    @Resource
    private lateinit var codeService: CodeService

    fun getLoginVerificationCode(): ServerLoginCode {
        //生成登陆ID
        val loginId = UUID.randomUUID().toString()

        //生成验证码
        val image = codeService.getLoginVerificationCode(loginId)

        val bos = ByteArrayOutputStream()
        ImageIO.write(image, "jpeg", bos)

        //生存浏览器可读图片
        val pic = "data:image/jpeg;base64," + BASE64Encoder().encode(bos.toByteArray())
                .replace("\n","")

        return ServerLoginCode(
                loginId = loginId,
                image = pic
        )
    }
}
