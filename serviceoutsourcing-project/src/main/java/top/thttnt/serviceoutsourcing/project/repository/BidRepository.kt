package top.thttnt.serviceoutsourcing.project.repository

import org.springframework.data.repository.CrudRepository
import top.thttnt.serviceoutsourcing.common.model.Bid

interface BidRepository : CrudRepository<Bid,Int>{

    fun findByProjectIdAndStudioId(projectId : Int,studioId : Int) : Bid?
}