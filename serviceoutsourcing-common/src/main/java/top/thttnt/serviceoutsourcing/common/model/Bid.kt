package top.thttnt.serviceoutsourcing.common.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0

    var projectId = 0

    var studioId = 0

    var quote = 0
}