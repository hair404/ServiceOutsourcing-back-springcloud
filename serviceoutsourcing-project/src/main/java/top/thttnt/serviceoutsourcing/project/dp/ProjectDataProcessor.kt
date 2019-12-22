package top.thttnt.serviceoutsourcing.project.dp

import org.springframework.stereotype.Component
import top.thttnt.serviceoutsourcing.project.repository.ProjectRepository
import javax.annotation.Resource

@Component
class ProjectDataProcessor{

    @Resource
    lateinit var projectRepository: ProjectRepository

    fun getInfo(){

    }
}
