package br.com.mir4.guild.manager.core.guild.exception

import br.com.mir4.guild.manager.core.config.BaseAPIException
import org.springframework.http.HttpStatus.NOT_FOUND

class GuildNotFoundException(msg: String) : BaseAPIException(msg, NOT_FOUND)
