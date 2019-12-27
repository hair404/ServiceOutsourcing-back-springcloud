package top.thttnt.serviceoutsourcing.common.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class ProjectRunning {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0

    var projectId = 0

    var studioId = 0

    var state = 0

    var price = 0

    var payAdvancedId = 0

    var payCompanyDepositId = 0

    var payStudioDepositId = 0

    var step = 0

    var totalStep = 0

}
