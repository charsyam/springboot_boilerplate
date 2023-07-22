package win.playcode.boiler.admin.common.exception

import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.HandlerMethod
import win.playcode.boiler.admin.common.log.Log
import javax.servlet.http.HttpServletRequest
import javax.validation.ValidationException

@RestControllerAdvice
@Order(Int.MIN_VALUE)
class ValidationExceptionHandler(
) {

    companion object : Log

    @ExceptionHandler(ValidationException::class)
    fun validationException(
        exception: ValidationException,
        httpServletRequest: HttpServletRequest,
        handler: HandlerMethod?
    ): ResponseEntity<*> {
        logger.error("",exception)
        return ResponseEntity
            .status(500)
            .body(
                    httpServletRequest.requestURI,
                )
    }
}
