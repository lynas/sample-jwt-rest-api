package com.lynas.springsecuritywithjwtdemo.service

import com.lynas.springsecuritywithjwtdemo.repository.AuthUserRepository
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service("userDetailsService")
class UserDetailServiceImpl(val appUserRepository: AuthUserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        appUserRepository.findByUsername(username)?.let {
            return User(it.username, it.password, listOf())
        } ?: throw UsernameNotFoundException(String.format("No User found with username: $username"))
    }

}

