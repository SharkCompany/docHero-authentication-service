package dochero.service.authenticaionservice.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value="api-gateway")
public interface AccountServiceFeignClient {
  @GetMapping("/account-service/api/account/ping")
  public String ping();
}
