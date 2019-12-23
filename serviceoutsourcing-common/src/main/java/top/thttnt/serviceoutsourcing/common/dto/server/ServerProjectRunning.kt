package top.thttnt.serviceoutsourcing.common.dto.server

data class ServerProjectRunning(
        val studioId: Int,
        val state: Int,
        val price: Int,
        val payAdvanced: ServerPayment?,
        val payCompanyDeposit: ServerPayment?,
        val payStudioDeposit: ServerPayment?,
        val step: Int,
        val totalStep: Int
)