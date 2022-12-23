package dochero.service.authenticationservice.exception;

import dochero.service.authenticationservice.constant.ValidationErrorMessage;

public class InvalidTokenException extends RuntimeException{
  public InvalidTokenException() {
    super(ValidationErrorMessage.INVALID_TOKEN);
  }
  public InvalidTokenException(String msg) {
    super(msg);
  }
}