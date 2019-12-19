package top.thttnt.serviceoutsourcing.common.utils.alipay

import com.alipay.api.AlipayApiException
import com.alipay.api.CertAlipayRequest
import com.alipay.api.DefaultAlipayClient
import com.alipay.api.request.AlipayFundTransUniTransferRequest
import com.alipay.api.request.AlipayTradePagePayRequest
import java.io.File

object AlipayTools {
    private val certPath = File("payment/appCertPublicKey_2016101700705840.crt").absolutePath
    private val alipayPublicCertPath = File("payment/alipayCertPublicKey_RSA2.crt").absolutePath
    private val rootCertPath = File("payment/alipayRootCert.crt").absolutePath
    private const val getway = "https://openapi.alipaydev.com/gateway.do"
    private const val appid = "2016101700705840"
    private const val privateId = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCNrHJcGz38MTVy9N7Vw7j4Y/8SKddn1pQh71iAa1NYym6GYTPo34JAzafKgFQ0yeZOEsnQO+7YVvaZGV9NPCRWq7lyZJt+sZwHmgqBy4nWGE5Fz0dUZj7gnfi4bnLLmyy+jgSOwMxjnE+lhn/wIXFz+XniABHd2ImwUk9rvi+nyOod8Xeymd/CSFX8J6MFCkRU6qh3rZfMc+zaeeqdcffnX7kCjK7ABnHF1rrLnUR1xBVSvyZFPSRsOI0sFVkcNV+44V+VGYNFzyimpXYBOG6QyV6fxC6FJUtfqjBBh40jZzJwMLOQJSaWQUAdWSxLosJJ2CrmVhZnoTeadk84s+I/AgMBAAECggEBAI0JvvN431VgO8CMjAYtCcil6/lOFDY4hVHfL7Dwp5G+PRekTwQm9knDMqZC6RGAabkRtT2LXLScpnQ1sQ13sQljfYOnKrGO6TEcJhee5EJyAgI3XuBjxXbhbQlWUwvlGD8/rMPcpd0IcLXtVxJciNCW7MKTpTXAaxpQZuZw+IpD0C0B5S7aEiH4zMpvGEHw4Vmo6ps8sZ/A+ksRwpU38HZjUKApcN54eWC2p/c5iD4GnetS6x+1FB4Atl7vdgAlDblVj0DWTrkgGFRSvN/dn0Jh6YNCgvzf1p1QahC5lflvnzZRcXa1XyZcAXF6Xu9QKDMPDFt4TbVOctCSPVoIXTkCgYEA85TWEk5z1yWCrPR74GVsBo35pnQw6P7En/J8AiY6lBfzsBedqoGdjxQNye68oJHFe+CP0inXEGmbbOFuZfGZn2aQiBHkcNn1qtrzJUk6NlY6o2SwEMfEM6l8orKK/0gaYdD/dokvTT6Vb3cAafLq6QHHtEiFBhONisc+sgX3lZUCgYEAlOWJHMZKGumsvsBML4s+yTwDvC5ogdnJjOcw8DgcpIQnHARRAFwNeDe7NniqcmXKS6MwlBcA2FI3Z35/dQeiq17zBIpYrp4wSa6h+9Q7Crd2hJ9n62hi/WTngQF68dCvEW1snzYvD/d1irrWZbsIR9Nep7F35Rf7vU8QTkklO4MCgYEA6s5nLm6CLLv0JFXptH8Qi6EBL/yByZkOYkGWWVwQzAHq361kR6F7CRoD0M1a+E6NWMU43xeGQkQLmAIYMh2cQZTVbtYQqNjzgShfi2nzu72T+T5umz53XHXdt9j1NPGEvRaDrPjBqGzUEQdeVsPcv5D/ukLOOPVQVJ+NSH+IE6ECgYB6yZM5rHfeG8e+AlyRdJYRYzF9VRQPyScB0IG2B7/vF6Ac5Z5a2o6tDeCQOg/tiO27VFcyOIoU2jAgY9v+CY7nHcTwgJpDXeZaTO39+3W5D7RoCACtzAaeCkEzTKIzmjFVANTrssY6t4oyHoPBnuxkNvW++oXx4cZSxkmfFYjjuwKBgQDvFN+1J77lCEyQ9xXMA2qZzLKv7O+lnAuXQ0EkJ0JIyjS3jx/T4j3evlrJaDQQ4/YSDHOPKsKolWIeIpFKUJ7WomxejuG1L2fRz24xRYt21rV4xTDFVjgzBAIjmYo62deiemM8AzhSDNeVLoj/kqbC74vxH0tay9LCOKhB7TTfVw=="
    @Throws(AlipayApiException::class)
    fun pay(payInfo: PayInfo, notifyURL: String?, returnURL: String?): String {
        val alipayClient = defaultClient
        val alipayRequest = AlipayTradePagePayRequest() // 创建API对应的request
        alipayRequest.notifyUrl = notifyURL // 在公共参数中设置回跳和通知地址
        alipayRequest.returnUrl = returnURL
        alipayRequest.bizContent = payInfo.toJson() // 填充业务参数
        return alipayClient.pageExecute(alipayRequest).body
    }

    @Throws(AlipayApiException::class)
    fun transfer(transferInfo: TransferInfo): Boolean {
        val alipayClient = defaultClient
        val request = AlipayFundTransUniTransferRequest()
        request.bizContent = transferInfo.toJsonString()
        val response = alipayClient.certificateExecute(request)
        return response.isSuccess
    }

    @get:Throws(AlipayApiException::class)
    private val defaultClient: DefaultAlipayClient
        private get() {
            val certAlipayRequest = CertAlipayRequest()
            certAlipayRequest.serverUrl = getway
            certAlipayRequest.appId = appid
            certAlipayRequest.privateKey = privateId
            certAlipayRequest.format = "json"
            certAlipayRequest.charset = "utf-8"
            certAlipayRequest.signType = "RSA2"
            certAlipayRequest.certPath = certPath
            certAlipayRequest.alipayPublicCertPath = alipayPublicCertPath
            certAlipayRequest.rootCertPath = rootCertPath
            return DefaultAlipayClient(certAlipayRequest)
        }
}