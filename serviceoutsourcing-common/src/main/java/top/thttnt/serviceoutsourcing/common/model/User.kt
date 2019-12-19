package top.thttnt.serviceoutsourcing.common.model

import top.thttnt.serviceoutsourcing.common.type.UserType
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Transient

@Entity
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0

    var telephone = ""

    var password = "";

    var nickname = "";

    var type = -1;

    var alipay = ""

    var realname = "";

    var email = "";

    var description = "";

    var avatar = "";

    var background = "";

    var deleted = false;

    @Transient
    fun getType(): UserType {
        return UserType.fromId(type)
    }

    @Transient
    fun setType(type: UserType) {
        this.type = type.id
    }
}