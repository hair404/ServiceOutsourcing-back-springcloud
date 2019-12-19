package top.thttnt.serviceoutsourcing.payment.repository

import org.springframework.data.repository.CrudRepository
import top.thttnt.serviceoutsourcing.common.model.Payment

interface PaymentRepository : CrudRepository<Payment, Int>