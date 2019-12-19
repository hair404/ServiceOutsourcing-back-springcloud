package top.thttnt.serviceoutsourcing.common.annotation

import top.thttnt.serviceoutsourcing.common.type.UserType

annotation class Authorization(val types: Array<UserType>)