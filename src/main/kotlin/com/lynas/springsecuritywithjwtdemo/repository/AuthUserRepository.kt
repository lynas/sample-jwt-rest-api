package com.lynas.springsecuritywithjwtdemo.repository

import com.lynas.springsecuritywithjwtdemo.model.AuthUser
import org.springframework.data.jpa.repository.JpaRepository

interface AuthUserRepository : JpaRepository<AuthUser, Long> {
    fun findByUsername(username: String) : AuthUser?
}