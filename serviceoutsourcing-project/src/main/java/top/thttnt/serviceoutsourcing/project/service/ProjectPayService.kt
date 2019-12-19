package top.thttnt.serviceoutsourcing.project.service

import org.springframework.context.annotation.Lazy
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import top.thttnt.serviceoutsourcing.common.service.FeignPayService
import top.thttnt.serviceoutsourcing.common.type.ErrorType
import top.thttnt.serviceoutsourcing.common.type.ProjectState
import top.thttnt.serviceoutsourcing.common.type.getException
import top.thttnt.serviceoutsourcing.project.repository.ProjectRepository
import top.thttnt.serviceoutsourcing.project.repository.ProjectRunningRepository
import javax.annotation.Resource

@Service
class ProjectPayService {

    @Resource
    @Lazy
    private lateinit var feignPayService: FeignPayService

    @Resource
    private lateinit var projectRepository: ProjectRepository

    @Resource
    private lateinit var projectRunningRepository: ProjectRunningRepository

    fun payCompanyDeposit(companyId: Int, projectId: Int): String {
        //判断项目是否存在
        val project = projectRepository.findByIdOrNull(projectId) ?: throw ErrorType.PROJECT_NOT_FOUND.getException()
        //判断项目是否运行
        val running = projectRunningRepository.findByIdOrNull(projectId)
                ?: throw ErrorType.PROJECT_NOT_RUNNING.getException()
        //判断项目状态
        if (running.state != ProjectState.PAYING.id) throw ErrorType.PROJECT_STATE_NOT_ALLOW.getException()

        //获取支付信息
        val info = feignPayService.pay((running.price * 0.1).toInt(), "${project.name}-项目定金")

        //设置支付信息
        running.payCompanyDepositId = info.paymentId

        //保存数据
        projectRunningRepository.save(running)

        return info.payForm
    }
}
