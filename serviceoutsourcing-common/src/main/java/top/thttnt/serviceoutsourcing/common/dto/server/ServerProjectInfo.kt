package top.thttnt.serviceoutsourcing.common.dto.server

data class ServerProjectInfo(
        val id: Int,
        val name: String,
        val tag: Int,
        val image: String,
        val owner: Int,
        val price: Int,
        val advanced: Int,
        val releaseTime: Long,
        val deadline: Long,
        val description: String,
        var runInfo: ServerProjectRunning?)

