package dochero.service.authenticationservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class LoginResponse extends BaseResponse{
  private String email;
  private String roleName;
  private String fullName;
  private String departmentId;
  private String accessToken;
  private Long expired;
}
