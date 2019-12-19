package top.thttnt.serviceoutsourcing.common.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class FileInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0

    var filename = ""

    var uuid = ""

    var type = -1

}