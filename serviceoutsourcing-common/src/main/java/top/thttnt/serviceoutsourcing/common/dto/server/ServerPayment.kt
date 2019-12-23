package top.thttnt.serviceoutsourcing.common.dto.server

import javax.persistence.Column
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

data class ServerPayment(
        val id: Int,
        val orderId: String,
        val amount: Int,
        val state: Int,
        val description: String
)