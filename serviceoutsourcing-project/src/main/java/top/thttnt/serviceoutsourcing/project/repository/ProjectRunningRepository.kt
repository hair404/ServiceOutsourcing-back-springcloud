package top.thttnt.serviceoutsourcing.project.repository

import org.springframework.data.repository.CrudRepository
import top.thttnt.serviceoutsourcing.common.model.ProjectRunning

interface ProjectRunningRepository : CrudRepository<ProjectRunning,Int> {
}