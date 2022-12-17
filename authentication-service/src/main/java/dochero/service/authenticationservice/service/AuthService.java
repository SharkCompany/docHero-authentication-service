package dochero.service.authenticationservice.service;

import dochero.service.authenticationservice.dto.account.AccountDTO;
import dochero.service.authenticationservice.dto.account.ValidateAccountRequestDTO;
import dochero.service.authenticationservice.dto.request.LoginRequest;
import dochero.service.authenticationservice.dto.response.LoginResponse;
import dochero.service.authenticationservice.exception.WrongPasswordException;
import dochero.service.authenticationservice.openfeign.AccountServiceFeignClient;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  private final AccountServiceFeignClient accountServiceFeignClient;

  public AuthService(AccountServiceFeignClient accountServiceFeignClient) {
    this.accountServiceFeignClient = accountServiceFeignClient;
  }

  public LoginResponse grantUser(LoginRequest loginRequest) {
    AccountDTO accountDTO;
    try {
       accountDTO = accountServiceFeignClient.validate(
          ValidateAccountRequestDTO
              .builder()
              .email(loginRequest.getEmail())
              .password(loginRequest.getPassword())
              .build()
      );
    } catch (Exception ex) {
      throw new WrongPasswordException();
    }

    return LoginResponse.builder()
        .email(accountDTO.getEmail())
        .roleName(accountDTO.getRoleName())
        .fullName(accountDTO.getFullName())
        .departmentId(accountDTO.getDepartmentId())
        .build();
  }
}
