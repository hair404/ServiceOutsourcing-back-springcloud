package top.thttnt.serviceoutsourcing.admin.service

import org.springframework.stereotype.Service
import top.thttnt.serviceoutsourcing.admin.repository.CategoryRepository
import javax.annotation.Resource

@Service
class ConfigService {

    @Resource
    private lateinit var categoryRepository: CategoryRepository

    fun getCategory(): List<CategoryParent> {
        val categories = categoryRepository.findAll()
        val categoryMap = HashMap<Int, CategoryParent>()
        categories.filter { it.parent == 0 }
                .forEach {
                    categoryMap[it.id] = CategoryParent(it.name, it.id, arrayListOf())
                }
        categories.filter { it.parent != 0 }
                .filter { categoryMap.containsKey(it.parent) }
                .forEach {
                    categoryMap[it.parent]!!.child.add(CategoryChild(it.name, it.id))
                }
        return categoryMap.values.toList()
    }

    data class CategoryParent(val name: String, val id: Int, val child: MutableList<CategoryChild>)

    data class CategoryChild(val name: String, val id: Int)
}