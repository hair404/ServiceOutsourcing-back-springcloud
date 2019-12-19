package top.thttnt.serviceoutsourcing.common.utils

fun String.toIntArray(): Array<Int> {
    if (this.isEmpty()) {
        return intArrayOf().toTypedArray()
    }
    return this.split(",")
            .map { it.toInt() }
            .toTypedArray()
}

fun Array<Int>.toDTOString(): String {
    val sb = StringBuilder()
    this.forEachIndexed { index, it ->
        sb.append(it)
        if (index != this.size - 1) {
            sb.append(",")
        }
    }
    return sb.toString()
}