package top.thttnt.serviceoutsourcing.common.utils.alipay

import com.alibaba.fastjson.annotation.JSONField

class PayeeInfo {
    @JSONField(name = "identity")
    var identity: String? = null
    @JSONField(name = "identity_type")
    var identityType = "ALIPAY_USER_ID"

    fun setAccount(account: String?) {
        identityType = "ALIPAY_USER_ID"
        identity = account
    }

    fun setUID(uid: String?) {
        identityType = "ALIPAY_LOGON_ID"
        identity = uid
    }
}