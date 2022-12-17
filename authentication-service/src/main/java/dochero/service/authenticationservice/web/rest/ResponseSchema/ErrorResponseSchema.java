package dochero.service.authenticationservice.web.rest.ResponseSchema;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponseSchema {
  public LocalDateTime timestamp = LocalDateTime.now();
  public HttpStatus statusCode;
  public Object messages;
}
