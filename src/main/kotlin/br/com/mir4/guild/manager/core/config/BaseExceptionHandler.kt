package br.com.mir4.guild.manager.core.config

import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice(basePackages = ["br.com.mir4.guild.manager"])
class BaseExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [BaseAPIException::class, IllegalArgumentException::class])
    fun handleAPIExceptions(ex: BaseAPIException, request: WebRequest):
        ResponseEntity<Any> = ResponseEntity(ex.errorBody, HttpHeaders(), ex.code)
}
