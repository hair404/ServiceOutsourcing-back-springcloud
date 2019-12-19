package top.thttnt.serviceoutsourcing.project.repository

import org.springframework.data.repository.CrudRepository
import top.thttnt.serviceoutsourcing.common.model.Step

interface StepRepository : CrudRepository<Step, Int> {

    fun countByProjectId(projectId: Int): Int

    fun findByProjectIdAndPart(projectId: Int, part: Int) : Step?
}