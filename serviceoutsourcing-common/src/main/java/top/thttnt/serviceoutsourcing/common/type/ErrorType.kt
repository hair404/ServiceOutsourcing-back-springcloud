package top.thttnt.serviceoutsourcing.common.type

import org.springframework.http.HttpStatus
import top.thttnt.serviceoutsourcing.common.exception.RuntimeException


enum class ErrorType(val code: Int, val description: String, val status: HttpStatus) {

    OK(1, "访问成功", HttpStatus.OK),
    REGISTER_NOT_ALLOWED(-1, "无法注册这类角色", HttpStatus.FORBIDDEN),
    REQUEST_TOO_FAST(-2, "访问过快", HttpStatus.TOO_MANY_REQUESTS),
    WRONG_TELEPHONE_VERIFICATION_CODE(-3, "错误的手机验证码", HttpStatus.FORBIDDEN),
    USER_EXISTS(-4, "用户已存在", HttpStatus.NOT_ACCEPTABLE),
    USER_NON_EXISTS(-5, "用户不存在", HttpStatus.NOT_FOUND),
    WRONG_LOGIN_VERIFICATION_CODE(-6, "错误的登陆验证码", HttpStatus.FORBIDDEN),
    WRONG_PASSWORD(-7, "错误密码", HttpStatus.FORBIDDEN),
    INVALID_TOKEN(-8, "无效的TOKEN", HttpStatus.FORBIDDEN),
    PERMISSION_DENIED(-9, "权限不足", HttpStatus.FORBIDDEN),
    UNSUPPORTED_FILE(-10, "不支持的文件类型", HttpStatus.BAD_REQUEST),
    UNSUPPORTED_USER_TYPE(-11, "该用户类型不支持该操作", HttpStatus.NOT_ACCEPTABLE),
    BID_NOT_FOUND(-12, "投标信息不存在", HttpStatus.NOT_FOUND),
    PROJECT_NOT_FOUND(-13, "项目信息不存在", HttpStatus.NOT_FOUND),
    PROJECT_RUNNING(-14, "项目已经开始了", HttpStatus.NOT_ACCEPTABLE),
    PRICE_LOWER_THAN_ADVANCED(-15, "首付款高于报价", HttpStatus.NOT_ACCEPTABLE),
    PROJECT_STATE_NOT_ALLOW(-16, "项目目前状态不允许此操作", HttpStatus.NOT_ACCEPTABLE),
    PROJECT_NOT_RUNNING(-17, "项目不处于运行状态", HttpStatus.NOT_ACCEPTABLE),
    TIME_RANGE_ERROR(-18, "时间设置错误", HttpStatus.NOT_ACCEPTABLE),
    TOTAL_MONEY_ERROR(-19, "金额错误", HttpStatus.NOT_ACCEPTABLE),
    STEP_AMOUNT_ERROR(-20, "项目步骤错误", HttpStatus.NOT_ACCEPTABLE),
    UNEXPECTED_ERROR(-21,"发生了未知的错误",HttpStatus.INTERNAL_SERVER_ERROR);

    companion object {
        fun fromCode(code: Int): ErrorType {
            ErrorType.values().forEach { if (code == it.code) return it }
            return ErrorType.OK
        }
    }
}

fun ErrorType.getException(): RuntimeException {
    return RuntimeException(this)
}