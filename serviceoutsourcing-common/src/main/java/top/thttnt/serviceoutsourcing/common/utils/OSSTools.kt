package top.thttnt.serviceoutsourcing.common.utils

import com.aliyun.oss.OSSClientBuilder
import java.io.ByteArrayInputStream

object OSSTools {

    private const val endpoint = "http://oss-cn-hangzhou.aliyuncs.com"
    private const val accessKeyId = "LTAI4FpwjngawSEnW7fe5NxX"
    private const val accessKeySecret = "PyQPK27YcIuiIsyQV5fJsI9XPrLbqb"
    private const val bucket = "zhongbaowuyou"
    private val ossClient = OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret)
    const val url = "http://zhongbaowuyou.oss-cn-hangzhou.aliyuncs.com/"

    fun uploadFile(path: String?, content: ByteArray?) {
        ossClient.putObject(bucket, path, ByteArrayInputStream(content))
    }

}