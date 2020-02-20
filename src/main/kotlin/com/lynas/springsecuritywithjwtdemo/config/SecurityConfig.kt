package com.lynas.springsecuritywithjwtdemo.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @field:Autowired
    lateinit var userDetailsService: UserDetailsService


    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/auth").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter::class.java)
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    @Autowired
    fun configureAuthentication(authenticationManagerBuilder: AuthenticationManagerBuilder) {
        authenticationManagerBuilder
                .userDetailsService<UserDetailsService>(userDetailsService)
                .passwordEncoder(BCryptPasswordEncoder())
    }

    @Bean
    fun authenticationTokenFilterBean(): AuthenticationTokenFilter {
        return AuthenticationTokenFilter().apply {
            setAuthenticationManager(super.authenticationManagerBean())
        }
    }


}