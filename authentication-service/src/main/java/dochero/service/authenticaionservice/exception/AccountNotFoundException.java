package dochero.service.authenticaionservice.exception;

import dochero.service.authenticaionservice.constant.ValidationErrorMessage;

public class AccountNotFoundException extends RuntimeException{
  public AccountNotFoundException() {
    super(ValidationErrorMessage.ACCOUNT_NOT_FOUND);
  }
}
