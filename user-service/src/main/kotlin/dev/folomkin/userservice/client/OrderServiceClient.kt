package dev.folomkin.userservice.client

import OrderDto
import feign.Logger
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.context.annotation.Bean
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable


@FeignClient(
    name = "order-service",
    url = "\${order-service.url}" //http://localhost:808
)
interface OrderServiceClient {
    @GetMapping("/orders/user/{userId}")
    fun getOrdersByUserId(@PathVariable("userId") userId: Long): List<OrderDto>
}

@Bean
fun feignLoggerLevel(): Logger.Level = Logger.Level.FULL