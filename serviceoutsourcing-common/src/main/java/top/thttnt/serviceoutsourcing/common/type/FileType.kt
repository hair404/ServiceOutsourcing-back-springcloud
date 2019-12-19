package top.thttnt.serviceoutsourcing.common.type

enum class FileType(val id: Int, val path: String) {

    UNKNOWN(-1, ""),
    AVATAR(1, "Avatar/"),
    PROJECT_IMAGE(2, "ProjectImage/");

    companion object {
        fun fromId(id: Int): FileType {
            return FileType.values().find { it.id == id } ?: FileType.UNKNOWN
        }
    }

}