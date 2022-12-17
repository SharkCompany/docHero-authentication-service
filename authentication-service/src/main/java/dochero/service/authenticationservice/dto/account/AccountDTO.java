package dochero.service.authenticationservice.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
public class AccountDTO {
  private String fullName;
  private String roleName;
  private String email;
  private String id;
  private String departmentId;
}
