package top.thttnt.serviceoutsourcing.user.dto

import org.springframework.stereotype.Component
import top.thttnt.serviceoutsourcing.common.dto.server.ServerUser
import top.thttnt.serviceoutsourcing.user.service.UserService
import javax.annotation.Resource

@Component
class UserDataProcessor {

    @Resource
    lateinit var userService: UserService

    fun getUserInfo(uid: Int): ServerUser {
        val user = userService.getUser(uid)
        //TODO CREDIT
        return ServerUser(
                id = user.id,
                nickname = user.nickname,
                avatar = user.avatar,
                img = user.background,
                email = user.email,
                tel = user.telephone,
                info = user.description,
                realname = user.description,
                credit = 0,
                tag = userService.getTagList(uid),
                type = user.type
        )
    }

}
