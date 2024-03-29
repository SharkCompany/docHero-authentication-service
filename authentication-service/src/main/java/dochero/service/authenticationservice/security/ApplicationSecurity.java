package dochero.service.authenticationservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(
    prePostEnabled = false, securedEnabled = false, jsr250Enabled = true
)
public class ApplicationSecurity {

  @Bean
  public PasswordEncoder  passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authConfig) throws Exception {
    return authConfig.getAuthenticationManager();
  }

  @Bean
  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    http.authorizeRequests()
//            .antMatchers("/item").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET, "/test", "/test/**").permitAll()
//                .antMatchers(HttpMethod.GET, "/item", "/catalog", "/item/search", "/catalog/search").permitAll()
//                .antMatchers("/auth/login", "/register").permitAll()
        .anyRequest().permitAll();

    http.exceptionHandling()
        .authenticationEntryPoint(
            (request, response, ex) -> {
              response.sendError(
                  HttpServletResponse.SC_UNAUTHORIZED,
                  ex.getMessage()
              );
            }
        );

//        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }
}