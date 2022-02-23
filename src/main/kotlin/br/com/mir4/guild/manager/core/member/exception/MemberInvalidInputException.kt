package br.com.mir4.guild.manager.core.member.exception

import br.com.mir4.guild.manager.core.config.BaseAPIException
import org.springframework.http.HttpStatus.BAD_REQUEST

class MemberInvalidInputException(msg: String) : BaseAPIException(msg, BAD_REQUEST)
