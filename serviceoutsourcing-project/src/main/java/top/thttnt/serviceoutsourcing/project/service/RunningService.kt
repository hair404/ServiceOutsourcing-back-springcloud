package top.thttnt.serviceoutsourcing.project.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import top.thttnt.serviceoutsourcing.common.dto.client.ClientStep
import top.thttnt.serviceoutsourcing.common.model.Step
import top.thttnt.serviceoutsourcing.common.type.ErrorType
import top.thttnt.serviceoutsourcing.common.type.ProjectState
import top.thttnt.serviceoutsourcing.common.type.getException
import top.thttnt.serviceoutsourcing.project.repository.ProjectRepository
import top.thttnt.serviceoutsourcing.project.repository.ProjectRunningRepository
import top.thttnt.serviceoutsourcing.project.repository.StepRepository
import java.util.*
import javax.annotation.Resource

@Service
class RunningService {

    @Resource
    private lateinit var projectRepository: ProjectRepository

    @Resource
    private lateinit var projectRunningRepository: ProjectRunningRepository

    @Resource
    private lateinit var stepRepository: StepRepository

    fun setForm(studioId: Int, projectId: Int, steps: Array<ClientStep>) {
        //判断项目是否存在
        val project = projectRepository.findByIdOrNull(projectId) ?: throw ErrorType.PROJECT_NOT_FOUND.getException()
        //判断项目是否运行
        val running = projectRunningRepository.findByIdOrNull(projectId)
                ?: throw ErrorType.PROJECT_NOT_RUNNING.getException()
        //判断项目状态
        if (running.state != ProjectState.SET_FORM.id) throw ErrorType.PROJECT_STATE_NOT_ALLOW.getException()
        //权限判断
        if (running.studioId != studioId) throw ErrorType.PERMISSION_DENIED.getException()

        //判断时间是否会超时
        var totalTime: Long = 0
        steps.forEach { totalTime += it.time }
        val finishDate = Date().apply {
            this.time = this.time + (totalTime - 1) * 3600 * 24 * 1000L
        }
        if (finishDate.after(project.deadline)) throw ErrorType.TIME_RANGE_ERROR.getException()

        //设置项目状态
        running.state = ProjectState.SET_PRICE.id

        //保存步骤
        stepRepository.saveAll(
                steps.mapIndexed { index, it ->
                    Step().apply {
                        this.name = it.name
                        this.description = it.description
                        this.time = it.time
                        this.part = index + 1
                        this.projectId = projectId
                    }
                }
        )
        //保存项目
        projectRepository.save(project)
    }

    fun setPrice(companyId: Int, projectId: Int, prices: Array<Int>) {
        //判断项目是否存在
        val project = projectRepository.findByIdOrNull(projectId) ?: throw ErrorType.PROJECT_NOT_FOUND.getException()
        //判断项目是否运行
        val running = projectRunningRepository.findByIdOrNull(projectId)
                ?: throw ErrorType.PROJECT_NOT_RUNNING.getException()
        //判断项目状态
        if (running.state != ProjectState.SET_PRICE.id) throw ErrorType.PROJECT_STATE_NOT_ALLOW.getException()
        //权限判断
        if (project.owner != projectId) throw ErrorType.PERMISSION_DENIED.getException()

        //判断金额是否相等
        var totalMoney = 0
        prices.forEach { totalMoney += it }
        if (totalMoney != running.price) throw ErrorType.TOTAL_MONEY_ERROR.getException()

        //判断步骤数量
        if (prices.size != stepRepository.countByProjectId(projectId)) throw ErrorType.STEP_AMOUNT_ERROR.getException()

        //设置项目状态
        running.state = ProjectState.CONFIRM.id

        //保存数据
        stepRepository.saveAll(prices.mapIndexed { index, price ->
            val step = stepRepository.findByProjectIdAndPart(projectId, index + 1)
                    ?: throw ErrorType.UNEXPECTED_ERROR.getException()
            step.price = price
            step
        })

        projectRunningRepository.save(running)
    }

    fun confirm(studioId: Int, projectId: Int) {
        //判断项目是否存在
        val project = projectRepository.findByIdOrNull(projectId) ?: throw ErrorType.PROJECT_NOT_FOUND.getException()
        //判断项目是否运行
        val running = projectRunningRepository.findByIdOrNull(projectId)
                ?: throw ErrorType.PROJECT_NOT_RUNNING.getException()
        //判断项目状态
        if (running.state != ProjectState.CONFIRM.id) throw ErrorType.PROJECT_STATE_NOT_ALLOW.getException()
        //权限判断
        if (running.studioId != studioId) throw ErrorType.PERMISSION_DENIED.getException()

        //修改项目状态
        running.state = ProjectState.PAYING.id

        //保存数据
        projectRunningRepository.save(running)
    }
}
