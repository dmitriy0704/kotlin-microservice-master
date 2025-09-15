package dev.folomkin.orderservice.controller

import OrderDto
import OrderWithUserDto
import dev.folomkin.orderservice.service.OrderService
import org.springframework.core.annotation.Order
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderController(private val orderService: OrderService) {

    @GetMapping("/{id}")
    fun getOrder(@PathVariable id: Long): OrderWithUserDto {
        return orderService.getOrderWithUser(id)
    }
}