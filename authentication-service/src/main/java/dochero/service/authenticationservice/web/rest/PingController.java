package dochero.service.authenticationservice.web.rest;

import dochero.service.authenticationservice.exception.ServiceCallingException;
import dochero.service.authenticationservice.openfeign.AccountServiceFeignClient;
import dochero.service.authenticationservice.openfeign.DepartmentServiceFeignClient;
import dochero.service.authenticationservice.openfeign.DocumentRevisionServiceFeignClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class PingController {

  @Autowired
  AccountServiceFeignClient accountServiceFeignClient;

  @Autowired
  DocumentRevisionServiceFeignClient documentRevisionServiceFeignClient;

  @Autowired
  DepartmentServiceFeignClient departmentServiceFeignClient;


  @GetMapping("")
  public String Greeting() {
    return "Hello From Authentication Service";
  }

  @GetMapping("/ping-to-account-service")
  public String PingToAccountService() {
    return accountServiceFeignClient.ping();
  }

  @GetMapping("/ping-to-document-service")
  public String PingToDocumentService() {
    String res;
    try {
      res = documentRevisionServiceFeignClient.ping();
    } catch (Exception ex) {
      throw new ServiceCallingException(ex.toString());
    }

    return res;
  }

  @GetMapping("/ping-to-department-service")
  public String PingToDepartmentService() {
    String res;
    try {
      res = departmentServiceFeignClient.ping();
    } catch (Exception ex) {
      throw new ServiceCallingException(ex.toString());
    }
    return res;
  }
}
