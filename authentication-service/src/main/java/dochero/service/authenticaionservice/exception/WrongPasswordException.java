package dochero.service.authenticaionservice.exception;

import dochero.service.authenticaionservice.constant.ValidationErrorMessage;

public class WrongPasswordException extends RuntimeException{
  public WrongPasswordException() {
    super(ValidationErrorMessage.WRONG_PASSWORD);
  }
}
