package top.thttnt.serviceoutsourcing.common.utils

import com.aliyuncs.CommonRequest
import com.aliyuncs.DefaultAcsClient
import com.aliyuncs.IAcsClient
import com.aliyuncs.exceptions.ClientException
import com.aliyuncs.http.MethodType
import com.aliyuncs.profile.DefaultProfile

/**
 * 手机短信操作的相关类
 */
object MessageTools {
    private const val ACCESS_ID = "LTAI4FpwjngawSEnW7fe5NxX"
    private const val ACCESS_SECRET = "PyQPK27YcIuiIsyQV5fJsI9XPrLbqb"
    private val profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_ID, ACCESS_SECRET)
    private val client: IAcsClient = DefaultAcsClient(profile)
    /**
     * 发送验证码短信
     * @param phoneNumber 手机号码
     * @param verifyCode 验证码
     * @throws ClientException
     */
    fun sendVerifyCode(phoneNumber: String?, verifyCode: String) {
        val request = CommonRequest()
        request.method = MethodType.POST
        request.domain = "dysmsapi.aliyuncs.com"
        request.version = "2017-05-25"
        request.action = "SendSms"
        request.putQueryParameter("RegionId", "cn-hangzhou")
        request.putQueryParameter("PhoneNumbers", phoneNumber)
        request.putQueryParameter("SignName", "人力外包服务平台")
        request.putQueryParameter("TemplateCode", "SMS_177554785")
        request.putQueryParameter("TemplateParam", "{\"code\":$verifyCode}")
        client.getCommonResponse(request)

    }
}