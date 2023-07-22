package win.playcode.boiler.api.common.exception

import org.springframework.core.annotation.Order
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindException
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.MissingRequestHeaderException
import org.springframework.web.bind.MissingServletRequestParameterException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.method.HandlerMethod
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException
import win.playcode.boiler.api.common.log.Log
import java.util.ArrayList
import java.util.function.Consumer
import java.util.stream.Collectors
import java.util.stream.StreamSupport
import javax.servlet.http.HttpServletRequest
import javax.validation.ConstraintViolation
import javax.validation.ConstraintViolationException

@RestControllerAdvice
@Order(value = Int.MAX_VALUE)
class BindingExceptionHandler(
) {
    companion object : Log

    /**
     * 헤더 누락 에러
     */
    @ExceptionHandler(MissingRequestHeaderException::class)
    fun headerException(
        exception: MissingRequestHeaderException,
        httpServletRequest: HttpServletRequest,
        handler: HandlerMethod?
    ): ResponseEntity<*> {
        logger.error("", exception)

        return ResponseEntity
            .status(400)
            .body(
                httpServletRequest.requestURI,
            )
    }

    /**
     * Parameter 누락
     */
    @ExceptionHandler(MissingServletRequestParameterException::class)
    fun validException(
        exception: MissingServletRequestParameterException,
        httpServletRequest: HttpServletRequest,
        handler: HandlerMethod?
    ): ResponseEntity<*> {
        logger.error("",exception)

        val message = "Query Parameter Type '${exception.parameterType}' , Name '${exception.parameterName}' 가 필요 합니다."

        return ResponseEntity
            .status(400)
            .body(httpServletRequest.requestURI + " : " + message)
    }

    /**
     * 잘못 된 Argument 를 보냈을때
     * ex) int parameter 에 string 형태로 보낼때
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException::class)
    fun validException(
        exception: MethodArgumentTypeMismatchException,
        httpServletRequest: HttpServletRequest,
        handler: HandlerMethod?
    ): ResponseEntity<*> {
        logger.error("",exception)

        val type = exception.requiredType.toString()
        val messageType = type.substring(type.lastIndexOf('.') + 1)
        val message = "Query Parameter Name '${exception.name}' 에는 ,  Type '${messageType}' 이(가) 필요 합니다. 잘못된 요청값 '${  exception.value}'"

        return ResponseEntity
            .status(400)
            .body(httpServletRequest.requestURI + " : " + message)
    }

    /**
     * Valid Exception
     */
    @ExceptionHandler(ConstraintViolationException::class)
    fun validException(
        exception: ConstraintViolationException,
        httpServletRequest: HttpServletRequest,
        handler: HandlerMethod?
    ): ResponseEntity<*> {
        logger.error("",exception)

        val errorList = ArrayList<String>()

        exception.constraintViolations.forEach(Consumer { error: ConstraintViolation<*> ->
            val stream =
                StreamSupport.stream(error.propertyPath.spliterator(), false)
            val list = stream.collect(Collectors.toList())
            val field = list[list.size - 1].name
            val message = error.message
            val invalidValue = error.invalidValue.toString()
            val errorMessage = "Field ${field} ${message} 잘못된 요청값 : ${invalidValue}"

            errorList.add(errorMessage)
        })

        val message = java.lang.String.join(" , ", errorList)
        return ResponseEntity
            .status(400)
            .body(httpServletRequest.requestURI + " : " + message)
    }

    /**
     * Request Body 없는 경우 에러
     */
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun validationException(
        exception: HttpMessageNotReadableException?,
        httpServletRequest: HttpServletRequest,
        handler: HandlerMethod?
    ): ResponseEntity<*> {
        logger.error("",exception)

        val message = "Request Body 가 비어 있습니다."
        return ResponseEntity
            .status(400)
            .body(httpServletRequest.requestURI + " : " + message)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun validException(
        exception: MethodArgumentNotValidException,
        httpServletRequest: HttpServletRequest,
        handler: HandlerMethod?
    ): ResponseEntity<*>  {
        logger.error("",exception)

        val errorList = ArrayList<String>()
        val bindingResult = exception.bindingResult
        bindingResult.allErrors.forEach(Consumer { error: ObjectError ->
            val field = error as FieldError
            val fieldName = field.field
            val message = field.defaultMessage
            val invalidValue =
                if (field.rejectedValue != null) field.rejectedValue.toString() else "null"
            val errorMessage = "Field ${fieldName} ${message} 잘못된 요청값 : ${invalidValue}"
            errorList.add(errorMessage)
        })

        val message = java.lang.String.join(" , ", errorList)
        return ResponseEntity
            .status(400)
            .body(httpServletRequest.requestURI + " : " + message)
    }

    @ExceptionHandler(BindException::class)
    fun validationException(
        exception: BindException,
        httpServletRequest: HttpServletRequest,
        handler: HandlerMethod?
    ): ResponseEntity<*> {
        logger.error("",exception)

        val errorList = ArrayList<String>()
        val bindingResult = exception.bindingResult
        bindingResult.allErrors.forEach(Consumer { error: ObjectError ->
            val field = error as FieldError
            val fieldName = field.field
            val message = field.defaultMessage
            val invalidValue =
                if (field.rejectedValue != null) field.rejectedValue.toString() else "null"
            val errorMessage = "Field ${fieldName} ${message} 잘못된 요청값 : ${invalidValue}"
            errorList.add(errorMessage)
        })
        val message = java.lang.String.join(" , ", errorList)

        return ResponseEntity
            .status(400)
            .body(httpServletRequest.requestURI + " : " + message)
    }
}