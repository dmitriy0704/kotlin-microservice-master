package dev.folomkin.orderservice.service

import OrderDto
import OrderWithUserDto
import dev.folomkin.orderservice.client.UserServiceClient
import org.springframework.stereotype.Service

@Service
class OrderService(
    private val userServiceClient: UserServiceClient
) {
    fun getOrderWithUser(orderId: Long): OrderWithUserDto {
        // Пример: Получение заказа из БД
        val order = OrderDto(
            id = orderId,
            userId = 1L,
            amount = 100.0,
            status = "CREATED"
        )

        val user = userServiceClient.getUserById(orderId)

        return OrderWithUserDto(
            orderId = order.id,
            user = user,
            amount = order.amount,
            status = order.status
        )
    }
}