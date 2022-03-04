package br.com.mir4.guild.manager.core.login.service

import br.com.mir4.guild.manager.core.config.userdetails.UserDetailsImpl
import br.com.mir4.guild.manager.core.login.exception.LoginInvalidInputException
import br.com.mir4.guild.manager.core.login.exception.LoginNotFoundException
import br.com.mir4.guild.manager.core.login.repository.LoginRepository
import br.com.mir4.guild.manager.model.login.Login
import br.com.mir4.guild.manager.model.login.request.LoginRequest
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class LoginService(
    private val loginRepository: LoginRepository,
    private val bCryptPasswordEncoder: BCryptPasswordEncoder
) {
    fun getById(loginId: UUID): Login? = loginRepository.findById(loginId)
        ?: throw LoginNotFoundException("Login not found")

    fun myself() = loginRepository.findMyself(getCurrentUsername())
        ?: throw LoginNotFoundException("Login not found")

    fun login(loginRequest: LoginRequest): String {
        validateFields(loginRequest)

        val login = loginRepository.findByUsername(loginRequest.username)

        if (!bCryptPasswordEncoder.matches(loginRequest.password, login?.password))
            throw LoginInvalidInputException("Login invalid input")

        return "${login?.username} successfully logged in"
    }

    fun create(loginRequest: LoginRequest): Login {
        validateFields(loginRequest)
        val login = buildLogin(loginRequest)

        try {
            loginRepository.save(login)
        } catch (ex: Exception) {
            throw LoginInvalidInputException("Login invalid input or username already exists")
        }

        return login
    }

    private fun validateFields(loginRequest: LoginRequest?) {
        try {
            loginRequest?.let {
                with(it) {
                    require(username.isNotEmpty()) { "Username cannot be empty" }
                    require(password.isNotEmpty()) { "Password cannot be empty" }
                }
            }
        } catch (ex: IllegalArgumentException) {
            throw LoginInvalidInputException("Login invalid input")
        }
    }

    private fun buildLogin(loginRequest: LoginRequest) = Login(
        id = UUID.randomUUID(),
        username = loginRequest.username,
        password = bCryptPasswordEncoder.encode(loginRequest.password)
    )

    private fun getCurrentUsername(): String? {
        val user = SecurityContextHolder
            .getContext()
            .authentication
            .principal as UserDetailsImpl

        return user.username
    }
}
