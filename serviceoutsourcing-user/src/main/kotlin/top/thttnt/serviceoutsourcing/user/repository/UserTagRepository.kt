package top.thttnt.serviceoutsourcing.user.repository

import org.springframework.data.repository.CrudRepository
import top.thttnt.serviceoutsourcing.common.model.UserTag

interface UserTagRepository : CrudRepository<UserTag,Int>