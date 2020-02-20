package com.lynas.springsecuritywithjwtdemo.config

import com.lynas.springsecuritywithjwtdemo.TOKEN_HEADER
import com.lynas.springsecuritywithjwtdemo.util.TokenUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class AuthenticationTokenFilter : UsernamePasswordAuthenticationFilter() {

    @Autowired
    lateinit var tokenUtils: TokenUtils

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

        val httpServletResponse = response as HttpServletResponse

        val httpRequest = request as HttpServletRequest
        httpRequest.getHeader(TOKEN_HEADER)?.let {
            try {
                val userDetails = tokenUtils.getUserDetailsFromToken(it)
                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                authentication.details = WebAuthenticationDetailsSource().buildDetails(httpRequest)
                SecurityContextHolder.getContext().authentication = authentication
            } catch (e: Exception) {
                e.printStackTrace()
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "The token is not valid.")
            }
        }
        chain.doFilter(request, response)
    }

}
