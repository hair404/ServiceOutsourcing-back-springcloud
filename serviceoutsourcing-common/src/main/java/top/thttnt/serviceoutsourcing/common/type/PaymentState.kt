package top.thttnt.serviceoutsourcing.common.type

enum class PaymentState(val id: Int) {

    PENDING(0),
    FAILURE(1),
    SUCCESS(2);
}