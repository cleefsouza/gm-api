package br.com.mir4.guild.manager.core.config.security

import br.com.mir4.guild.manager.core.config.utils.JWTUtil
import br.com.mir4.guild.manager.core.config.utils.WebMappings
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var userDetailsService: UserDetailsService

    @Autowired
    private lateinit var jwtUtil: JWTUtil

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()

        http
            .authorizeRequests()
            .antMatchers(HttpMethod.POST, "${WebMappings.LOGIN}/signin")
            .permitAll()
            .anyRequest()
            .authenticated()

        http.addFilter(JWTAuthenticationFilter(authenticationManager(), jwtUtil))
        http.addFilter(JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService))

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder())
    }
}
