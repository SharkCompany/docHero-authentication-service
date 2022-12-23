package dochero.service.authenticationservice.openfeign;

import dochero.service.authenticationservice.dto.account.AccountDTO;
import dochero.service.authenticationservice.dto.account.ValidateAccountRequestDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("account-service")
public interface AccountServiceFeignClient {
  @GetMapping("/")
  String ping();

  @PostMapping("/validate")
  public AccountDTO validate(@RequestBody ValidateAccountRequestDTO accountPayload);

  @GetMapping("/document-revision-service/document-revision/test")
  public String pingToDocumentService();

  @GetMapping("/account/{accountId}/departments")
  public List<String> getDocumentsOfUser(@PathVariable String accountId);




}
