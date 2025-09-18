package dev.folomkin.authservice.security.controller

import dev.folomkin.authservice.security.utils.JwtUtils
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController {


    @GetMapping
    fun getToken(): String {
//        return JwtUtils().createJwt("Authentication")
//       return JwtUtils().printJwt()
        val resObj = JwtUtils().printJwt()
        return "${resObj.component1()}: \n\n ${resObj.component2()}"

    }

    @GetMapping("/demo")
    fun demo(): String {
        return "Demo Security"
    }
}