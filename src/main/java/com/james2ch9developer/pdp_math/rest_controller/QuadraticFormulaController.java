package com.james2ch9developer.pdp_math.rest_controller;

import com.james2ch9developer.pdp_math.producer.QuadraticFormulaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Value;

@RestController
@RequestMapping("/quadraticFormula")
@SuppressWarnings("PMD.AtLeastOneConstructor")
public class QuadraticFormulaController {
  @Value("${spring.kafka.topic}")
  private String topic;
  @Autowired
  private QuadraticFormulaProducer producer;
  @PostMapping(value = "/v1/quadraticFormula")
  public final ResponseEntity<String> quadraticFormula(@RequestBody final String coefficients,
         @AuthenticationPrincipal final Authentication authentication) {
//  public final ResponseEntity<String> quadraticFormula(@RequestBody final String coefficients) {
    // I will see if I can get the username from the JWT token.
    // Publish the whole string to the queue.
    if (authentication != null) {
      System.out.println("authentication.getName() = " + authentication.getName());
      String userEmail = authentication.getName();
      String data = coefficients + ", " + userEmail;
      producer.send(topic, data);
//    producer.send(topic, coefficients);
      return ResponseEntity.ok("Data processed successfully.");
    }
    return ResponseEntity.status(401).body("Unauthorized");
  }
}
