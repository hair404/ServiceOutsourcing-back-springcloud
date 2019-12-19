package top.thttnt.serviceoutsourcing.common.model

import javax.persistence.Entity
import javax.persistence.Id

@Entity
class UserTag {

    @Id
    var uid = 0

    var tags = ""

}