package top.thttnt.serviceoutsourcing.common.model

import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
open class Project{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0

    var name = ""

    var tag = 0

    var img = ""

    var owner = 0

    var price = 0

    var advanced = 0

    var releaseTime = Date()

    var deadline = Date()

    var description = ""


}