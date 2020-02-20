package com.lynas.springsecuritywithjwtdemo.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*

@Component
class TokenUtils {

    private val secret = "Secret" // should be taken from external source e.g. property file or env file

    fun getUsernameFromToken(token: String) = getClaimsFromToken(token).subject
            ?: throw RuntimeException("Unable to get username from token String")

    private fun getClaimsFromToken(token: String): Claims = Jwts.parser()
            .setSigningKey(this.secret)
            .parseClaimsJws(token)
            .body ?: throw RuntimeException("Unable to parse claim from token string")


    fun getUserDetailsFromToken(token: String): UserDetails {
        return User(getUsernameFromToken(token), "", listOf())
    }


    fun generateToken(userDetails: UserDetails): String {
        val claims = HashMap<String, Any>()
        claims["sub"] = userDetails.username
        claims["created"] = Date()
        return this.generateToken(claims)
    }

    private fun generateToken(claims: Map<String, Any>): String {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact()
    }

}