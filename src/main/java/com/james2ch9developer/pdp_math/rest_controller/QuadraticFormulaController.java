package com.james2ch9developer.pdp_math.rest_controller;

import com.james2ch9developer.pdp_math.producer.QuadraticFormulaProducer;
//import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/quadraticFormula")
@SuppressWarnings("PMD.AtLeastOneConstructor")
//@SecurityRequirement(name = "Keycloak")
public class QuadraticFormulaController {
  @Value("${spring.kafka.topic}")
  private String topic;
  @Autowired
  private QuadraticFormulaProducer producer;
  @PostMapping(value = "/v1/quadraticFormula")
  public final ResponseEntity<String> quadraticFormula(@RequestBody final String coefficients) {
//    String userEmail = "james2ch9developer@gmail";
//    String data = coefficients + ", " + userEmail;
//    producer.send(topic, data);
    producer.send(topic, coefficients);
    return ResponseEntity.ok("Data processed successfully.");
  }
}
