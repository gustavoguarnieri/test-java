package br.com.blz.testjava.config

import br.com.blz.testjava.exception.NotFoundException
import br.com.blz.testjava.exception.UnprocessableException
import br.com.blz.testjava.model.response.BaseException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime


@ControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

  @ExceptionHandler(NotFoundException::class)
  private fun handleNotFoundException(
    ex: NotFoundException,
    request: WebRequest
  ): ResponseEntity<BaseException> {
    val baseException = BaseException(
      LocalDateTime.now(),
      HttpStatus.NOT_FOUND.value(),
      HttpStatus.NOT_FOUND.reasonPhrase,
      ex.localizedMessage,
      (request as ServletWebRequest).request.requestURI
    )
    return ResponseEntity(baseException, HttpHeaders(), HttpStatus.NOT_FOUND)
  }

  @ExceptionHandler(UnprocessableException::class)
  private fun handleNotFoundException(
    ex: UnprocessableException,
    request: WebRequest
  ): ResponseEntity<BaseException> {
    val baseException = BaseException(
      LocalDateTime.now(),
      HttpStatus.UNPROCESSABLE_ENTITY.value(),
      HttpStatus.UNPROCESSABLE_ENTITY.reasonPhrase,
      ex.localizedMessage,
      (request as ServletWebRequest).request.requestURI
    )
    return ResponseEntity(baseException, HttpHeaders(), HttpStatus.UNPROCESSABLE_ENTITY)
  }
}
