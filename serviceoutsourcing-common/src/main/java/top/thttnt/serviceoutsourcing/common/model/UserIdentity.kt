package top.thttnt.serviceoutsourcing.common.model

import com.sun.javafx.beans.IDProperty
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class UserIdentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0

    var uid = 0

    var token = ""

    var lastTime: Long = 0

}