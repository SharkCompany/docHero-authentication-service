package dochero.service.authenticationservice.openfeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("department-service")
public interface DepartmentServiceFeignClient {
  @GetMapping("/test")
  String ping();

}
