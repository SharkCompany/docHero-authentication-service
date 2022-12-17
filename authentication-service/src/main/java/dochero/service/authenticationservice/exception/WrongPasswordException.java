package dochero.service.authenticationservice.exception;

import dochero.service.authenticationservice.constant.ValidationErrorMessage;

public class WrongPasswordException extends RuntimeException{
  public WrongPasswordException() {
    super(ValidationErrorMessage.WRONG_PASSWORD);
  }
  public WrongPasswordException(String msg) {
    super(msg);
  }
}
