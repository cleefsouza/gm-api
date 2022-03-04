package br.com.mir4.guild.manager.core.config.security

import br.com.mir4.guild.manager.core.config.userdetails.UserDetailsImpl
import br.com.mir4.guild.manager.core.config.utils.JWTUtil
import br.com.mir4.guild.manager.core.config.utils.WebMappings
import br.com.mir4.guild.manager.core.login.exception.LoginNotFoundException
import br.com.mir4.guild.manager.model.login.request.LoginRequest
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter : UsernamePasswordAuthenticationFilter {

    private var jwtUtil: JWTUtil

    constructor(authenticationManager: AuthenticationManager, jwtUtil: JWTUtil) : super() {
        this.authenticationManager = authenticationManager
        this.jwtUtil = jwtUtil

        this.setFilterProcessesUrl("${WebMappings.LOGIN}/signin")
    }

    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse?): Authentication? {
        try {
            val (username, password) = ObjectMapper().readValue(request.inputStream, LoginRequest::class.java)
            val token = UsernamePasswordAuthenticationToken(username, password)

            return authenticationManager.authenticate(token)
        } catch (e: Exception) {
            throw LoginNotFoundException("Login not found")
        }
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse,
        chain: FilterChain?,
        authResult: Authentication
    ) {
        val username = (authResult.principal as UserDetailsImpl).username
        val token = jwtUtil.generateToken(username)

        response.addHeader("Authorization", "Bearer $token")
        response.addHeader("access-control-expose-headers", "Authorization")
    }
}
