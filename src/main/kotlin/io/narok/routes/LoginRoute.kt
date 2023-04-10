package io.narok.routes

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.narok.models.User
import java.util.*

fun Application.loginRouting() {
    val secret = environment.config.property("jwt.secret").getString()
    val issuer = environment.config.property("jwt.issuer").getString()
    val audience = environment.config.property("jwt.audience").getString()
    val expiryInDays = environment.config.property("jwt.expiry_in_days").getString().toInt()

    routing {
        post("/login") {
            val user = call.receive<User>()
            val token = JWT.create().withAudience(audience).withIssuer(issuer).withClaim("username", user.username)
                .withExpiresAt(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * expiryInDays))
                .sign(Algorithm.HMAC256(secret))
            call.respond(hashMapOf("token" to token))
        }
    }
}