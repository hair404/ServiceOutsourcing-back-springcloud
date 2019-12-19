package top.thttnt.serviceoutsourcing.common.type

enum class ProjectState(val id: Int) {

    SET_FORM(1),
    SET_PRICE(2),
    CONFIRM(3),
    PAYING(4),
    RUNNING(5),
    RATING(6),
    FINISH(7),
    CANCEL(8),
    ARGUE(9);

}