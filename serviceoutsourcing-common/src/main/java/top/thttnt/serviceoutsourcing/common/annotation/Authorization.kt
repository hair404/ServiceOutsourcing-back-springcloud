package top.thttnt.serviceoutsourcing.common.annotation

import org.apache.tomcat.util.security.PermissionCheck
import top.thttnt.serviceoutsourcing.common.type.UserType

annotation class Authorization(val types: Array<UserType>)