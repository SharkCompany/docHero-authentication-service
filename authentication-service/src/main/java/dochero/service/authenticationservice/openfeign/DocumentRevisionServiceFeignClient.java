package dochero.service.authenticationservice.openfeign;

import dochero.service.authenticationservice.dto.account.AccountDTO;
import dochero.service.authenticationservice.dto.account.ValidateAccountRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("document-revision-service")
public interface DocumentRevisionServiceFeignClient {
  @GetMapping("/document-revision/test")
  String ping();

}
