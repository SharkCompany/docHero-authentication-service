package dochero.service.authenticationservice.exception;

import dochero.service.authenticationservice.constant.ValidationErrorMessage;

public class AccountNotFoundException extends RuntimeException{
  public AccountNotFoundException() {
    super(ValidationErrorMessage.ACCOUNT_NOT_FOUND);
  }
}
