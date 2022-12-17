package dochero.service.authenticaionservice.exception;

import dochero.service.authenticaionservice.web.rest.ResponseSchema.ErrorResponseSchema;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApplicationExceptionHandler {

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({
      DuplicateEmailException.class})
  public ResponseEntity<Object> InvalidRequest(RuntimeException exception) {
    var responseBody = ErrorResponseSchema.builder()
        .timestamp(LocalDateTime.now())
        .messages(exception.getMessage())
        .statusCode(HttpStatus.BAD_REQUEST)
        .build();
    return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler({
      AccountNotFoundException.class})
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<Object> NotFoundExceptionHandler(RuntimeException exception) {
    var responseBody = ErrorResponseSchema.builder()
        .timestamp(LocalDateTime.now())
        .messages(exception.getMessage())
        .statusCode(HttpStatus.NOT_FOUND)
        .build();
    return new ResponseEntity<>(responseBody, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler({
      WrongPasswordException.class})
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseEntity<Object> handleWrongPasswordException(WrongPasswordException exception) {
    var responseBody = ErrorResponseSchema.builder()
        .timestamp(LocalDateTime.now())
        .messages(exception.getMessage())
        .statusCode(HttpStatus.UNAUTHORIZED)
        .build();
    return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler({
      ValidationException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<Object> InvalidRequest(ValidationException exception, WebRequest request) {
    var responseBody = ErrorResponseSchema.builder()
        .timestamp(LocalDateTime.now())
        .messages(exception.getListDefaultMessages())
        .statusCode(HttpStatus.BAD_REQUEST)
        .build();
    return new ResponseEntity<>(responseBody, HttpStatus.BAD_REQUEST);
  }
}
