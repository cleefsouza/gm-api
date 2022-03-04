package br.com.mir4.guild.manager.core.member.exception

import br.com.mir4.guild.manager.core.config.error.BaseAPIException
import org.springframework.http.HttpStatus.NOT_FOUND

class MemberNotFoundException(msg: String) : BaseAPIException(msg, NOT_FOUND)
