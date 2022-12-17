package dochero.service.authenticationservice.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value="api-gateway")
public interface AccountServiceFeignClient {
  @GetMapping("/account-service/")
  public String ping();
}
