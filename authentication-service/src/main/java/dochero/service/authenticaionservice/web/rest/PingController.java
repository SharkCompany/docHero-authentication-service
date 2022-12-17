package dochero.service.authenticaionservice.web.rest;

import dochero.service.authenticaionservice.openfeign.AccountServiceFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class PingController {

  final AccountServiceFeignClient accountServiceFeignClient;

  @GetMapping("/")
  public String Greeting() {
    return "Hello From Authentication Service";
  }

  @GetMapping("/ping-to-account-service")
  public String PingToAccountService() {
    return accountServiceFeignClient.ping();
  }

}
