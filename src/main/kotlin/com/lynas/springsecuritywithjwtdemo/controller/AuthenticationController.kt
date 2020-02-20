package com.lynas.springsecuritywithjwtdemo.controller

import com.lynas.springsecuritywithjwtdemo.dto.AuthenticationRequest
import com.lynas.springsecuritywithjwtdemo.util.TokenUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/auth")
class AuthenticationController(
        val authenticationManager: AuthenticationManager,
        val userDetailsService: UserDetailsService,
        val tokenUtils: TokenUtils

        ) {

    @PostMapping
    fun authenticationRequest(@RequestBody authenticationRequest: AuthenticationRequest): String {
        SecurityContextHolder.getContext().authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                        authenticationRequest.username,
                        authenticationRequest.password
                )
        )
        return tokenUtils.generateToken(userDetailsService.loadUserByUsername(authenticationRequest.username))
    }

}