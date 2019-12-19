package top.thttnt.serviceoutsourcing.common.model

import javax.persistence.*

@Entity
class Step{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0

    var projectId = 0

    var part = 0

    var state = 0

    var time = 0

    var price = 0

    @Column(columnDefinition = "TEXT NOT NULL")
    var description = ""

    @Column(columnDefinition = "TEXT NOT NULL")
    var name = ""

    var payPrice = 0

    var payId = 0
}