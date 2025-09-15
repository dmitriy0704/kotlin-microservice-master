package dev.folomkin.orderservice.client

import UserDto
import feign.Logger
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(
    name = "user-service",
    url = "\${user-service.url}" // http://localhost:8083: можно убрать, если используешь Eureka
)
interface UserServiceClient {
    @GetMapping("/users/{id}")
    fun getUserById(@PathVariable id: Long): UserDto
}

@Bean
fun feignLoggerLevel(): Logger.Level = Logger.Level.FULL

//@Component
//class UserServiceFallback : UserServiceClient {
//    override fun getUserById(id: Long): UserDto {
//        return UserDto(id, "Unknown", "unknown@example.com") // Заглушка
//    }
//}
