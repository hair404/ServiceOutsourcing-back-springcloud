package top.thttnt.serviceoutsourcing.admin.repository

import org.springframework.data.repository.CrudRepository
import top.thttnt.serviceoutsourcing.common.model.Category

interface CategoryRepository : CrudRepository<Category, Int>