package top.thttnt.serviceoutsourcing.common.utils.alipay

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.annotation.JSONField
import java.util.*

class TransferInfo {
    @JSONField(name = "out_biz_no")
    var outBizNo: String? = null
    @JSONField(name = "trans_amount")
    var transAmount: String? = null
    @JSONField(name = "product_code")
    var productCode = "TRANS_ACCOUNT_NO_PWD"
    @JSONField(name = "biz_scene")
    var bizScene = "DIRECT_TRANSFER"
    @JSONField(name = "payee_info")
    var payeeInfo: PayeeInfo? = null

    /**
     * 随机生成订单ID
     */
    fun generateRandomOutBizNo(): String? {
        outBizNo = UUID.randomUUID().toString().replace("-", "")
        return outBizNo
    }

    /**
     * 通过支付宝登陆身份转账
     *
     * @param account 邮箱/手机
     */
    fun setAccount(account: String?) {
        payeeInfo = PayeeInfo()
        payeeInfo!!.setAccount(account)
    }

    /**
     * 通过支付宝UID转账
     *
     * @param uid 支付宝UID
     */
    fun setUID(uid: String?) {
        payeeInfo = PayeeInfo()
        payeeInfo!!.setUID(uid)
    }

    fun toJsonString(): String {
        return JSON.toJSONString(this)
    }
}