package dochero.service.authenticationservice.service;

import dochero.service.authenticationservice.constant.CommonConstants;
import dochero.service.authenticationservice.dto.account.AccountDTO;
import dochero.service.authenticationservice.dto.account.ValidateAccountRequestDTO;
import dochero.service.authenticationservice.dto.request.LoginRequest;
import dochero.service.authenticationservice.dto.response.LoginResponse;
import dochero.service.authenticationservice.dto.response.ValidateTokenResponse;
import dochero.service.authenticationservice.exception.AccountNotFoundException;
import dochero.service.authenticationservice.exception.ServiceCallingException;
import dochero.service.authenticationservice.exception.WrongPasswordException;
import dochero.service.authenticationservice.openfeign.AccountServiceFeignClient;
import dochero.service.authenticationservice.security.JwtUtils;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

  @Autowired
  JwtUtils jwtUtils;
  @Autowired
  AccountServiceFeignClient accountServiceFeignClient;

  public ValidateTokenResponse validateToken(String token) {
    jwtUtils.validateAuthToken(token);
    String id = jwtUtils.getUserIdFromJWT(token);
    AccountDTO accountDTO;

    try {
      accountDTO = accountServiceFeignClient.getAccount(id);
    } catch (Exception ex) {
      throw new ServiceCallingException("Can not call to account service to get account info. Error: "+ex);
    }

    if (Objects.isNull(accountDTO)) throw new AccountNotFoundException();

    List<String> departments;

    try {
      departments = accountServiceFeignClient.getDocumentsOfUser(id);
    }
    catch (Exception ex) {
      throw new ServiceCallingException("Can not call to account service to get departments of user. Error: "+ex.toString());
    }


    return ValidateTokenResponse.builder()
        .email(accountDTO.getEmail())
        .roleName(accountDTO.getRoleName())
        .fullName(accountDTO.getFullName())
        .departmentIDs(departments)
        .userId(accountDTO.getId())
        .build();
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
      throw new WrongPasswordException(ex.toString());
    }

    List<String> departments;

    try {
      departments = accountServiceFeignClient.getDocumentsOfUser(accountDTO.getId());
    } catch (Exception ex) {
      throw new ServiceCallingException(
          "Can not call to Account Service To Get Departments. Error: " + ex);
    }

    return LoginResponse.builder()
        .email(accountDTO.getEmail())
        .roleName(accountDTO.getRoleName())
        .fullName(accountDTO.getFullName())
        .about(accountDTO.getAbout())
        .avatar(accountDTO.getAvatar())
        .coverPhoto(accountDTO.getCoverPhoto())
        .description(accountDTO.getDescription())
        .title(accountDTO.getTitle())
        .location(accountDTO.getLocation())
        .id(accountDTO.getId())
        .departmentIDs(departments)
        .accessToken(
            jwtUtils.generateAccessToken(accountDTO)
        )
        .expired(CommonConstants.EXPIRE_DURATION)
        .build();
  }
}
