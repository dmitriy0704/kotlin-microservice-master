package dev.folomkin.authservice.security.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain


//@Configuration
class SecurityConfig {

//    @Bean
//    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
//        http
//            .csrf { it.disable() }
//            .authorizeHttpRequests { auth ->
//                auth
//                    .requestMatchers("/public/**").permitAll()
//                    .anyRequest().authenticated()
//            }
//            .httpBasic {} // простая Basic Auth для проверки
//        return http.build()
//    }
}