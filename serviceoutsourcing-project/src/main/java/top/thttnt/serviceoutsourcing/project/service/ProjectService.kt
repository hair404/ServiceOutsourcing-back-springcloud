package top.thttnt.serviceoutsourcing.project.service

import org.springframework.stereotype.Service
import top.thttnt.serviceoutsourcing.common.model.Project
import top.thttnt.serviceoutsourcing.project.repository.ProjectRepository
import java.util.*
import javax.annotation.Resource

@Service
class ProjectService {

    @Resource
    lateinit var projectRepository: ProjectRepository


    fun createProject(name: String, deadline: Date, description: String, img: String, owner: Int, price: Int, tag: Int, advanced: Int) {
        val project = Project().apply {
            this.name = name
            this.deadline = deadline
            this.description = description
            this.img = img
            this.owner = owner
            this.price = price
            this.tag = tag
            this.advanced = advanced
        }

        projectRepository.save(project)
    }
}