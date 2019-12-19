package top.thttnt.serviceoutsourcing.common.type

enum class StepState(val id: Int) {

    UNKNOWN(-1),
    WAIT(0),
    RUNNING(1),
    JUDGING(2),
    REJECT(3),
    STUDIO_PUNISHED(4),
    PAYING(5),
    PAYING_DELAY(6),
    COMPANY_PUNISHED(7),
    ARGUE(8),
    FINISH(9);
}