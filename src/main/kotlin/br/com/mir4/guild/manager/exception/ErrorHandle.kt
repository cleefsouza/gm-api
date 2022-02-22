package br.com.mir4.guild.manager.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest

@ControllerAdvice
class ErrorHandler {

    @ExceptionHandler(value = [ClassNotFoundException::class])
    fun illegalArgumentException(request: HttpServletRequest, exception: Exception): ResponseEntity<ErrorResponse> {
        val errorResponse = ErrorResponse(
            statusCode = HttpStatus.NOT_FOUND.value(),
            message = exception.message!!
        )

        return ResponseEntity.badRequest().body(errorResponse)
    }
}

data class ErrorResponse(val statusCode: Int, val message: String)
