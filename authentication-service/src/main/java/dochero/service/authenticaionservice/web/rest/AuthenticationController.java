package dochero.service.authenticaionservice.web.rest;


import dochero.service.authenticaionservice.dto.request.LoginRequest;
import dochero.service.authenticaionservice.dto.response.LoginResponse;
import dochero.service.authenticaionservice.exception.ValidationException;
import dochero.service.authenticaionservice.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthService authService;

  @Operation(summary = "Login By Email And Password")
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Authorized",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = LoginResponse.class))}),
      @ApiResponse(responseCode = "404", description = "Account Not Found",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = Object.class))
          }),
      @ApiResponse(responseCode = "401", description = "Unauthorized",
          content = {@Content(mediaType = "application/json",
              schema = @Schema(implementation = Object.class))
          }),
  })
  @PostMapping("/login")
  public ResponseEntity<LoginResponse> login(
      @RequestBody @Valid LoginRequest loginRequest,
      BindingResult errors
  ) {
    if (errors.hasErrors()) {
      throw new ValidationException(errors);
    }
    return new ResponseEntity<>(authService.grantUser(loginRequest), HttpStatus.OK);
  }
}
