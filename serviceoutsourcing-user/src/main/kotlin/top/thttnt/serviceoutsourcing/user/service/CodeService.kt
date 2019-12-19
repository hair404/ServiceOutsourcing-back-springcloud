package top.thttnt.serviceoutsourcing.user.service

import com.alibaba.fastjson.JSON
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import top.thttnt.serviceoutsourcing.common.type.ErrorType
import top.thttnt.serviceoutsourcing.common.type.getException
import top.thttnt.serviceoutsourcing.common.utils.MessageTools
import top.thttnt.serviceoutsourcing.user.utils.CodeImage
import java.awt.image.BufferedImage
import java.util.concurrent.TimeUnit
import javax.annotation.Resource
import kotlin.random.Random

@Service
class CodeService {
    @Resource
    private lateinit var redisTemplate: RedisTemplate<String, Any>

    /**
     * 发送手机验证码
     */
    fun sendTelephoneVerificationCode(telephone: String) {

        //生成验证码
        val code = Random.nextInt(100000, 999999).toString()

        setVerificationCode("tel-$telephone", code)

        MessageTools.sendVerifyCode(telephone, code)
    }

    /**
     * 校验手机验证码
     */
    fun checkTelephoneVerificationCode(telephone: String, code: String): Boolean {
        return checkVerificationCode("tel-$telephone", code)
    }

    fun getLoginVerificationCode(loginId: String): BufferedImage {
        val codeImage = CodeImage()

        setVerificationCode("login-$loginId", codeImage.code)

        return codeImage.image
    }

    fun checkLoginVerificationCode(loginId: String, code: String): Boolean {
        return checkVerificationCode("login-$loginId", code)
    }

    private fun setVerificationCode(key: String, code: String) {
        //获取验证码数据
        val vc = JSON.toJavaObject(redisTemplate.opsForValue().get(key) as JSON?, VerificationCode::class.java)
        //判断是否过快访问
        if (vc != null) {
            if (System.currentTimeMillis() - vc.time < 30 * 1000) {
                throw ErrorType.REQUEST_TOO_FAST.getException()
            }
        }

        //储存新的code
        val newVc = VerificationCode(code, System.currentTimeMillis())
        redisTemplate.opsForValue().set(key, newVc)
        redisTemplate.expire(key, 5, TimeUnit.MINUTES)

    }

    private fun checkVerificationCode(key: String, code: String): Boolean {
        val vc = JSON.toJavaObject(redisTemplate.opsForValue().get(key) as JSON?, VerificationCode::class.java)
                ?: return false
        //判断是否超时
        if (System.currentTimeMillis() - vc.time > 5 * 60 * 1000) return false
        return vc.code == code
    }

    data class VerificationCode(val code: String, val time: Long)
}