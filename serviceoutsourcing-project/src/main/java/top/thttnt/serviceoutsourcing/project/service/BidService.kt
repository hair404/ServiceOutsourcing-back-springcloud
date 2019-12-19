package top.thttnt.serviceoutsourcing.project.service

import org.springframework.context.annotation.Lazy
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import top.thttnt.serviceoutsourcing.common.model.Bid
import top.thttnt.serviceoutsourcing.common.model.ProjectRunning
import top.thttnt.serviceoutsourcing.common.service.FeignUserService
import top.thttnt.serviceoutsourcing.common.type.ErrorType
import top.thttnt.serviceoutsourcing.common.type.ProjectState
import top.thttnt.serviceoutsourcing.common.type.UserType
import top.thttnt.serviceoutsourcing.common.type.getException
import top.thttnt.serviceoutsourcing.project.repository.BidRepository
import top.thttnt.serviceoutsourcing.project.repository.ProjectRepository
import top.thttnt.serviceoutsourcing.project.repository.ProjectRunningRepository
import javax.annotation.Resource

@Service
class BidService {

    @Resource
    private lateinit var bidRepository: BidRepository

    @Resource
    private lateinit var projectRepository: ProjectRepository

    @Resource
    private lateinit var projectRunningRepository: ProjectRunningRepository

    @Lazy
    @Resource
    private lateinit var feignUserService: FeignUserService

    fun bid(projectId: Int, studioId: Int, quote: Int) {
        //判断角色是否存在和角色类型
        val exist = feignUserService.exist(studioId)

        if (exist.type != UserType.STUDIO.id) throw ErrorType.UNSUPPORTED_USER_TYPE.getException()

        //判断项目是否存在
        val project = projectRepository.findByIdOrNull(projectId) ?: throw ErrorType.PROJECT_NOT_FOUND.getException()

        //判断报价是否低于首付款
        if (project.advanced > quote) throw ErrorType.PRICE_LOWER_THAN_ADVANCED.getException()

        val bid = bidRepository.findByProjectIdAndStudioId(projectId, studioId) ?: Bid()
        bid.apply {
            this.projectId = projectId
            this.studioId = studioId
            this.quote = quote
        }

        //保存数据
        bidRepository.save(bid)
    }

    fun pick(companyId: Int, bidId: Int) {
        val bid = bidRepository.findByIdOrNull(bidId) ?: throw ErrorType.BID_NOT_FOUND.getException()
        val project = projectRepository.findByIdOrNull(bid.projectId)
                ?: throw ErrorType.PROJECT_NOT_FOUND.getException()

        //判断该项目是否属于此公司
        if (project.owner != companyId) throw ErrorType.PERMISSION_DENIED.getException()

        //判断项目是否已经开始
        if (projectRunningRepository.existsById(project.id)) throw ErrorType.PROJECT_RUNNING.getException()

        val running = ProjectRunning().apply {
            this.projectId = project.id
            this.price = bid.quote
            this.state = ProjectState.SET_FORM.id
            this.studioId = bid.studioId
        }

        //储存开始信息
        projectRunningRepository.save(running)
    }
}