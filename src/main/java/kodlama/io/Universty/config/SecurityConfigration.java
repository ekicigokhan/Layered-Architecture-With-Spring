package kodlama.io.Universty.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;

@EnableWebSecurity
public class SecurityConfigration extends WebSecurityConfiguration {

  public SecurityConfigration(HttpSecurity httpSecurity) throws Exception {

    httpSecurity.httpBasic();

    httpSecurity
        .authorizeHttpRequests()
        .requestMatchers("/api/v1/students/add")
        .authenticated()
        .and()
        .authorizeHttpRequests()
        .requestMatchers("/api/v1/students/getall")
        .permitAll();
  }
}
