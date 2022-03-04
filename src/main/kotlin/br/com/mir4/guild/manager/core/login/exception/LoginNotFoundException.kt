package br.com.mir4.guild.manager.core.login.exception

import br.com.mir4.guild.manager.core.config.error.BaseAPIException
import org.springframework.http.HttpStatus.NOT_FOUND

class LoginNotFoundException(msg: String) : BaseAPIException(msg, NOT_FOUND)
