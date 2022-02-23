package br.com.mir4.guild.manager.core.config

import org.springframework.http.HttpStatus

abstract class BaseAPIException(
    private val msg: String,
    val code: HttpStatus
) : RuntimeException(msg) {
    val errorBody
        get() = ErrorBody(
            statusCode = code.value(),
            error = code.reasonPhrase,
            message = msg
        )
}

data class ErrorBody(val statusCode: Int, val error: String, val message: String)
