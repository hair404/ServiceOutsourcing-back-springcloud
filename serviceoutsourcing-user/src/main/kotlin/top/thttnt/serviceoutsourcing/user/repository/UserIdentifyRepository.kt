package top.thttnt.serviceoutsourcing.user.repository

import org.springframework.data.repository.CrudRepository
import top.thttnt.serviceoutsourcing.common.model.UserIdentity

interface UserIdentifyRepository : CrudRepository<UserIdentity,Int>{

    fun findByToken(token : String) : UserIdentity?
}