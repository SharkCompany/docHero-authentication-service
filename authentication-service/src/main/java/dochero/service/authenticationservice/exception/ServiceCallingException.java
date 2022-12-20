package dochero.service.authenticationservice.exception;

import dochero.service.authenticationservice.constant.ValidationErrorMessage;

public class ServiceCallingException extends RuntimeException{
  public ServiceCallingException() {
    super(ValidationErrorMessage.SERVICE_CALLING_EXCEPTION);
  }
  public ServiceCallingException(String msg) {
    super(msg);
  }
}