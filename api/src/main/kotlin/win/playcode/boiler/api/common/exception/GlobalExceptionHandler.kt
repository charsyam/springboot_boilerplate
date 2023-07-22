package win.playcode.boiler.api.common.exception

import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.NoHandlerFoundException
import win.playcode.boiler.api.common.log.Log
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
@Order(value = Int.MAX_VALUE)
class GlobalExceptionHandler(
) {
    companion object : Log

    @ExceptionHandler(Exception::class)
    fun exception(
        exception: Exception,
        httpServletRequest: HttpServletRequest,
        handlerMethod: HandlerMethod?
    ): ResponseEntity<*> {
        logger.error("",exception)

        return ResponseEntity
            .status(500)
            .body(
                httpServletRequest.requestURI,
            )
    }

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handle404(exception: NoHandlerFoundException, httpServletRequest: HttpServletRequest):  ResponseEntity<*> ? {
        logger.error("",exception)
        val message = "존재하지 않는 Uri or Method 입니다. Uri or Http Method 를 확인해주세요"

        return ResponseEntity
            .status(404)
            .body(httpServletRequest.requestURI + " : " + message)
    }
}