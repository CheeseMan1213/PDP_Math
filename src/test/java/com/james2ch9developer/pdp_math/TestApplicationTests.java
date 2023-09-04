package com.james2ch9developer.pdp_math;

import org.junit.jupiter.api.Test;
import org.springframework.boot.system.JavaVersion;
// import org.springframework.boot.system.SystemProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.SpringVersion;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@DirtiesContext
class TestApplicationTests {

    @Test
    public void contextLoads() {
      // Verifies the spring framework for version.
      assertEquals("6.0.11", SpringVersion.getVersion());
      // VerifiesJDK version.
      //    I do not really care about the more specific version number.
      //    Just that it is 17.
      // assertEquals("17.0.8", SystemProperties.get("java.version"));
      // Verifies Java version.
      assertEquals("17", JavaVersion.getJavaVersion().toString());
    }

}
