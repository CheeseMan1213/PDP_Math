package com.james2ch9developer.pdp_math;

//import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
//import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
//import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@SecurityScheme(
//  name = "Keycloak",
//  openIdConnectUrl = "http://localhost:8081/realms/master/.well-known/openid-configuration",
//  scheme = "bearer",
//  type = SecuritySchemeType.OPENIDCONNECT,
//  in = SecuritySchemeIn.HEADER
//)
public class PdpMathApplication {

  public static void main(final String[] args) {
    SpringApplication.run(PdpMathApplication.class, args);
  }

}
