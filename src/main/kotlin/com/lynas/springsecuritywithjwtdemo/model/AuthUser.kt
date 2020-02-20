package com.lynas.springsecuritywithjwtdemo.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class AuthUser(
        @Id
        @GeneratedValue
        val id:Long? = null,
        val username:String,
        val password:String)

