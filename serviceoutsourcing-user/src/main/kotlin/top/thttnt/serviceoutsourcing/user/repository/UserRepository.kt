package top.thttnt.serviceoutsourcing.user.repository

import org.springframework.data.repository.CrudRepository
import top.thttnt.serviceoutsourcing.common.model.User

interface UserRepository : CrudRepository<User, Int> {

    fun findByTelephone(telephone: String): User?
}