package dochero.service.authenticationservice.openfeign;

import dochero.service.authenticationservice.dto.account.AccountDTO;
import dochero.service.authenticationservice.dto.account.ValidateAccountRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "api-gateway", path = "account-service")
public interface AccountServiceFeignClient {
  @GetMapping("/")
  String ping();

  @PostMapping("/api/account/validate")
  public AccountDTO validate(@RequestBody ValidateAccountRequestDTO accountPayload);


}
