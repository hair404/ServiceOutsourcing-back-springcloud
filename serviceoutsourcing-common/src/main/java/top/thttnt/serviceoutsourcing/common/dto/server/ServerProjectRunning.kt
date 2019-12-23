package top.thttnt.serviceoutsourcing.common.dto.server

data class ServerProjectRunning(
        val studioId: Int,
        val state: Int,
        val price: Int,
        var payAdvanced: ServerPayment?,
        var payCompanyDeposit: ServerPayment?,
        var payStudioDeposit: ServerPayment?,
        val step: Int,
        val totalStep: Int
)