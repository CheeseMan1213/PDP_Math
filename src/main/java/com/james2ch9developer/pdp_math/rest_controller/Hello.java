package com.james2ch9developer.pdp_math.rest_controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
// I am suppressing this warning because this is a Spring-boot rest controller.
// This class does not need a constructor, and I do not need PMD telling me otherwise.
@SuppressWarnings("PMD.AtLeastOneConstructor")
public class Hello {
  @GetMapping(value = "/v1/hello", produces = "text/plain")
  public final String sayHello() {
    return "Hello.";
  }
  // URL: http://localhost:8080/v1/hello/hello
}
