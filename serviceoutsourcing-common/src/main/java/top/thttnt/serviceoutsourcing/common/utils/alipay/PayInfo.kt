package top.thttnt.serviceoutsourcing.common.utils.alipay

class PayInfo {
    //订单号
    var outTradeNo: String? = null
    //销售产品码
    var productCode = "FAST_INSTANT_TRADE_PAY"
    //价格
    var totalAmount = 0.0
    //订单标题
    var subject: String? = null

    fun toJson(): String {
        return ("{"
                + "\"out_trade_no\":\"" + outTradeNo + "\","
                + "\"product_code\":\"" + productCode + "\","
                + "\"total_amount\":\"" + totalAmount + "\","
                + "\"subject\":\"" + subject + "\""
                + "}")
    }
}