package top.thttnt.serviceoutsourcing.common.model

import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0

    var orderId = ""

    var amount = 0

    var state = 0

    @Column(columnDefinition = "TEXT NOT NULL")
    var description = ""

}