package top.thttnt.serviceoutsourcing.project.controller

import com.alibaba.fastjson.JSON
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Lazy
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import top.thttnt.serviceoutsourcing.common.annotation.Authorization
import top.thttnt.serviceoutsourcing.common.dto.client.ClientStep
import top.thttnt.serviceoutsourcing.common.service.FeignFileService
import top.thttnt.serviceoutsourcing.common.type.FileType
import top.thttnt.serviceoutsourcing.common.type.UserType
import top.thttnt.serviceoutsourcing.project.service.BidService
import top.thttnt.serviceoutsourcing.project.service.ProjectService
import top.thttnt.serviceoutsourcing.project.service.RunningService
import java.util.*
import javax.annotation.Resource
import javax.persistence.AttributeOverride
import javax.servlet.http.HttpServletRequest

@RestController
class ProjectController {

    @Resource
    lateinit var projectService: ProjectService

    @Resource
    lateinit var bidService: BidService

    @Resource
    lateinit var runningService: RunningService

    @Lazy
    @Resource
    lateinit var feignFileService: FeignFileService

    @RequestMapping("create")
    @Authorization(types = [UserType.COMPANY])
    fun create(@RequestParam name: String, @RequestParam deadline: Long,
               @RequestParam description: String, @RequestParam img: MultipartFile,
               @RequestParam price: Int, @RequestParam tag: Int,
               @RequestParam pia: Int, @RequestAttribute uid: Int): ResponseEntity<Any> {

        //格式化Date
        val deadlineDate = Date(deadline)

        //上传图片
        val imgUuid = feignFileService.upload(FileType.PROJECT_IMAGE.id, img)

        //创建项目
        projectService.createProject(
                name = name,
                deadline = deadlineDate,
                description = description,
                img = imgUuid.uuid,
                owner = uid,
                price = price,
                tag = tag,
                advanced = pia
        )

        return ResponseEntity.noContent().build<Any>()
    }

    @RequestMapping("bid")
    @Authorization(types = [UserType.STUDIO])
    fun bid(@RequestParam projectId: Int, @RequestParam quote: Int,
            @RequestAttribute uid: Int): ResponseEntity<Any> {
        bidService.bid(projectId = projectId,
                studioId = uid,
                quote = quote)
        return ResponseEntity.noContent().build<Any>()
    }

    @RequestMapping("pick")
    @Authorization(types = [UserType.COMPANY])
    fun pick(@RequestParam bid: Int, @RequestAttribute uid: Int): ResponseEntity<Any> {
        bidService.pick(companyId = uid, bidId = bid)
        return ResponseEntity.noContent().build<Any>()
    }

    @RequestMapping("setForm")
    @Authorization(types = [UserType.STUDIO])
    fun setForm(@RequestParam projectId: Int, @RequestParam steps: String,
                @RequestParam uid: Int): ResponseEntity<Any> {
        val stepArray = JSON.parseArray(steps, ClientStep::class.java).toTypedArray()
        runningService.setForm(projectId = projectId, studioId = uid, steps = stepArray)
        return ResponseEntity.ok().build<Any>()
    }

    @RequestMapping("setPrice")
    @Authorization(types = [UserType.COMPANY])
    fun setPrice(@RequestParam projectId: Int, @RequestParam stepPrice: String,
                 @RequestAttribute uid: Int): ResponseEntity<Any> {
        val prices = JSON.parseArray(stepPrice, Int::class.java).toTypedArray()
        runningService.setPrice(projectId = projectId, companyId = uid, prices = prices)
        return ResponseEntity.ok().build<Any>()
    }

    @RequestMapping("confirm")
    @Authorization(types = [UserType.STUDIO])
    fun confirm(@RequestParam projectId: Int, @RequestAttribute uid: Int): ResponseEntity<Any> {
        runningService.confirm(studioId = uid, projectId = projectId)
        return ResponseEntity.ok().build<Any>()
    }

}