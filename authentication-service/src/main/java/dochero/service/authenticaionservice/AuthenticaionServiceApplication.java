package dochero.service.authenticaionservice;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Authentication Service API", version = "1.0", description = "Account Information"))
public class AuthenticaionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticaionServiceApplication.class, args);
	}

}
