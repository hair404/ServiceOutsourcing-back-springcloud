package top.thttnt.serviceoutsourcing.project.dp

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import top.thttnt.serviceoutsourcing.common.dto.server.ServerProjectInfo
import top.thttnt.serviceoutsourcing.common.dto.server.ServerProjectRunning
import top.thttnt.serviceoutsourcing.common.type.ErrorType
import top.thttnt.serviceoutsourcing.common.type.getException
import top.thttnt.serviceoutsourcing.project.repository.ProjectRepository
import top.thttnt.serviceoutsourcing.project.repository.ProjectRunningRepository
import javax.annotation.Resource

@Component
class ProjectDataProcessor {

    @Resource
    private lateinit var projectRepository: ProjectRepository

    @Resource
    private lateinit var projectRunningRepository: ProjectRunningRepository

    fun getInfo(projectId: Int) {
        val project = projectRepository.findByIdOrNull(projectId) ?: throw ErrorType.PROJECT_NOT_FOUND.getException()
        val dtoProject = ServerProjectInfo(
                id = project.id,
                name = project.name,
                tag = project.tag,
                image = project.img,
                owner = project.owner,
                price = project.price,
                advanced = project.advanced,
                releaseTime = project.releaseTime.time,
                deadline = project.deadline.time,
                description = project.description,
                runInfo = null
        )

        //添加RUN信息
        val running = projectRunningRepository.findByIdOrNull(projectId)
        if (running != null){
            dtoProject.runInfo = ServerProjectRunning(
                    studioId = running.studioId,
                    state = running.state,
                    price = running.price,
                    payAdvanced = null,
                    payCompanyDeposit = null,
                    payStudioDeposit = null,
                    step = running.step,
                    totalStep = running.totalStep
            )
            if (running.payAdvancedId != 0){

            }
        }
    }

}
