package br.com.mir4.guild.manager.core.config.userdetails

import br.com.mir4.guild.manager.core.login.exception.LoginNotFoundException
import br.com.mir4.guild.manager.core.login.repository.LoginRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl : UserDetailsService {

    @Autowired
    private lateinit var loginRepository: LoginRepository

    override fun loadUserByUsername(username: String?): UserDetails {
        val login = loginRepository.findByUsername(username) ?: throw LoginNotFoundException("Login not found")

        return UserDetailsImpl(login)
    }
}
