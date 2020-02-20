package com.lynas.springsecuritywithjwtdemo

import com.lynas.springsecuritywithjwtdemo.model.AuthUser
import com.lynas.springsecuritywithjwtdemo.repository.AuthUserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootApplication
class SpringSecurityWithJwtDemoApplication {
	@Bean
	fun init(appUserRepository: AuthUserRepository) = CommandLineRunner {
		appUserRepository.save(
				AuthUser(username = "lynas", password = BCryptPasswordEncoder().encode("123456")))
	}
}

fun main(args: Array<String>) {
	runApplication<SpringSecurityWithJwtDemoApplication>(*args)
}


const val TOKEN_HEADER = "Authorization"