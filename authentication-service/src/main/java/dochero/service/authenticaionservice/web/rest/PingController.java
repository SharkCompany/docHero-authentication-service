package dochero.service.authenticaionservice.web.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class PingController {
  @GetMapping("/")
  public String Greeting() {
    return "Hello From Authenticaion Service";
  }
}
