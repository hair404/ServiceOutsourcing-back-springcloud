package top.thttnt.serviceoutsourcing.common.dto.server

import top.thttnt.serviceoutsourcing.common.response.ResultBean
import top.thttnt.serviceoutsourcing.common.type.ErrorType

class ServerUser(val id: Int,
                 val nickname: String,
                 val avatar: String,
                 val img: String,
                 val tag: Array<Int>,
                 val email: String,
                 val tel: String,
                 val info: String,
                 val realname: String,
                 val credit: Int,
                 val type: Int)