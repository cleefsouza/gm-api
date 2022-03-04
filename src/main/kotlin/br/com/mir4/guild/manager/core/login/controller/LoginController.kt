package br.com.mir4.guild.manager.core.login.controller

import br.com.mir4.guild.manager.core.config.utils.WebMappings
import br.com.mir4.guild.manager.core.login.service.LoginService
import br.com.mir4.guild.manager.model.login.request.LoginRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping(WebMappings.LOGIN)
class LoginController(private val loginService: LoginService) {

    @GetMapping("/{loginId}")
    @ResponseStatus(HttpStatus.OK)
    fun get(@PathVariable loginId: UUID) = loginService.getById(loginId)

    @GetMapping("/me")
    @ResponseStatus(HttpStatus.OK)
    fun me() = loginService.myself()

    @PostMapping("/signin")
    @ResponseStatus(HttpStatus.ACCEPTED)
    fun login(@RequestBody loginRequest: LoginRequest) = loginService.login(loginRequest)

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody loginRequest: LoginRequest) = loginService.create(loginRequest)
}
