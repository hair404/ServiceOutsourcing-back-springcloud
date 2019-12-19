package top.thttnt.serviceoutsourcing.common.response

import top.thttnt.serviceoutsourcing.common.type.ErrorType

open class ResultBean(val code: Int, val description: String, val content: Any?) {

    constructor(type: ErrorType, content: Any) : this(type.code, type.description, content)

    companion object {
        fun fromErrorType(type: ErrorType): ResultBean {
            return ResultBean(type.code, type.description, null)
        }

        fun fromData(data: Any): ResultBean {
            return ResultBean(ErrorType.OK.code, ErrorType.OK.description, data)
        }

        fun fromAny(obj: Any): ResultBean {
            if (obj is ResultBean) {
                return obj
            }
            return if (obj is ErrorType) {
                fromErrorType(obj)
            } else {
                fromData(obj)
            }
        }
    }
}