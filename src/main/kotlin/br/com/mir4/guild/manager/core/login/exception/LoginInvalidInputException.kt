package br.com.mir4.guild.manager.core.login.exception

import br.com.mir4.guild.manager.core.config.error.BaseAPIException
import org.springframework.http.HttpStatus.BAD_REQUEST

class LoginInvalidInputException(msg: String) : BaseAPIException(msg, BAD_REQUEST)
