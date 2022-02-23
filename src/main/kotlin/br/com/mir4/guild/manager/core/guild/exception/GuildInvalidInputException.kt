package br.com.mir4.guild.manager.core.guild.exception

import br.com.mir4.guild.manager.core.config.BaseAPIException
import org.springframework.http.HttpStatus.BAD_REQUEST

class GuildInvalidInputException(msg: String) : BaseAPIException(msg, BAD_REQUEST)
