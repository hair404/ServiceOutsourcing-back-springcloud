package top.thttnt.serviceoutsourcing.common.type

enum class UserType(val id: Int) {

    UNKNOWN(-1),
    STUDIO(1),
    COMPANY(0),
    MANAGER(2),
    EXPERT(3);

    companion object {
        fun fromId(id: Int): UserType {
            return values().firstOrNull { it.id == id } ?: UNKNOWN
        }
    }
}