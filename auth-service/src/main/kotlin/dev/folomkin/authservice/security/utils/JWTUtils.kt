package dev.folomkin.authservice.security.utils

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.temporal.ChronoUnit
import java.util.*
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

data class JwtRes(
    val token: String,
    val parseToken: String
)

// -> 86400000 = 1 день
@Component
class JwtUtils {


    val secret = "5JzoMbk6E5qIqHSuBTgeQCARtUsxAkBiHwdjXOSW8kWdXzYmP3X51C0"

    val token = createJwt()

    val res = parseJwt(token)

    fun printJwt(): JwtRes {
        return JwtRes(
            token,
            "Parse: \n${res?.header} \n${res?.payload} \n${res?.signature}"
        )
    }


    // -> Генерация токена
    fun createJwt(): String {

        val username = "Authentication"

        val key: SecretKey = SecretKeySpec(
            Base64.getDecoder().decode(secret),
            Jwts.SIG.HS256.key().build().algorithm
        )

        return Jwts.builder()
            .subject(username)
            .claim("id", "abc123")
            .claim(
                "role",
                "admin"
            )/*.addClaims(Map.of("id", "abc123", "role", "admin"))*/
            .issuer("TestAuthServer")
            .issuedAt(Date.from(Instant.now()))
            .expiration(
                Date.from(
                    Instant.now().plus(10, ChronoUnit.MINUTES)
                )
            ) //.expiration(Date(System.currentTimeMillis() + expirationTime))
            .signWith(key)
            .compact()
    }

    //-> Парсинг токена
    fun parseJwt(token: String?): Jws<Claims?>? {
        // Recommended to be stored in Secret
        val hmacKey: SecretKey = SecretKeySpec(
            Base64.getDecoder().decode(secret),
            Jwts.SIG.HS256.key().build().algorithm
        )

        val jwt: Jws<Claims?>? =
            Jwts
                .parser()
                .verifyWith(hmacKey)
                .build()
                .parseSignedClaims(token)

        return jwt
    }

}

