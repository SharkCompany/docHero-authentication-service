package dochero.service.authenticationservice.dto.response;

import java.util.List;
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
  private List<String> departmentIDs;
  private String accessToken;
  private Long expired;
  private String title;
  private String avatar;
  private String coverPhoto;
  private String about;
  private String id;
  private String description;
  private String location;
}
