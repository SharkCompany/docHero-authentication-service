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
  private String description;
  private String title;
  private String avatar;
  private String coverPhoto;
  private String about;
  private String roleName;
  private String email;
  private String location;
  private String id;
  private String department;
  private String[] departmentIDs;
}
