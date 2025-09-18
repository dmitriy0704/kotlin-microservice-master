package dev.folomkin.authservice.security.config.mvcconfig

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain


@Configuration
@EnableWebSecurity //-> Включает поддержку веб-безопасности
class WebSecurityConfig {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain
    // -> Определяет цепочку фильтров безопасности
    {
        http
            .authorizeHttpRequests { requests -> //-> Указывает, какие URL требуют аутентификации
                requests
                    .requestMatchers("/", "/home")
                    .permitAll() //-> Разрешаем доступ без аутентификации
                    .requestMatchers("/admin").hasRole("ADMIN")
                    .anyRequest()
                    .authenticated() //-> Все остальные запросы требуют аутентификации
            }
            .formLogin { form -> //-> Настраивает форму логина (по умолчанию POST на /login)
                form
                    .loginPage("/login") //-> Страница логина
                    .permitAll() //-> Разрешаем доступ к странице логина
            }
            .logout { logout ->
                logout.permitAll() //-> Разрешаем логаут
            }
        return http.build()
    }


    @Bean
    fun userDetailsService(): UserDetailsService
    //-> Создает in-memory пользователя для тестирования.
    {
        val user = User.withDefaultPasswordEncoder()
            .username("user")
            .password("password")
            .roles("USER")
            .build()
        return InMemoryUserDetailsManager(user)
    }
}