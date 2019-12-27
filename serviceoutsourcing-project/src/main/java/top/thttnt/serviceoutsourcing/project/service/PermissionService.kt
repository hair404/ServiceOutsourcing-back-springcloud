package top.thttnt.serviceoutsourcing.project.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository
import org.springframework.stereotype.Service
import top.thttnt.serviceoutsourcing.common.type.ErrorType
import top.thttnt.serviceoutsourcing.common.type.getException
import top.thttnt.serviceoutsourcing.project.repository.ProjectRepository
import top.thttnt.serviceoutsourcing.project.repository.ProjectRunningRepository
import javax.annotation.Resource

@Service
class PermissionService{

    @Resource
    private lateinit var projectRepository: ProjectRepository

    @Resource
    private lateinit var projectRunningRepository: ProjectRunningRepository

    fun checkProject(uid : Int,projectId : Int){
        val project = projectRepository.findByIdOrNull(projectId) ?: throw ErrorType.PERMISSION_DENIED.getException()
        if (project.owner == uid) return
        val running = projectRunningRepository.findByProjectId(projectId) ?: return
        if (running.studioId == uid) return
        throw ErrorType.PERMISSION_DENIED.getException()
    }
}