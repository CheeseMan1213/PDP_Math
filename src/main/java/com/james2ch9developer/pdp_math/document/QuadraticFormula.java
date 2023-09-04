package com.james2ch9developer.pdp_math.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "quadraticFormula")
@Data
@AllArgsConstructor
public class QuadraticFormula {

  private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(QuadraticFormula.class);

  @Id
  private String userEmail;
  private double a;
  private double b;
  private double c;
  private double answer1;
  private double answer2;

  // Method to retrieve the coefficients used in the calculation
  public final void printCoefficients() {
    LOGGER.info("Coefficients: a=" + a + ", b=" + b + ", c=" + c);
  }

  // Builder class for QuadraticFormula
  public static class Builder {
    private double a;
    private double b;
    private double c;
    private String userEmail;

    public final QuadraticFormula.Builder setCoefficientsAndUserEmail(final String[] coefficientsAndUserEmail) {

      if (coefficientsAndUserEmail == null || coefficientsAndUserEmail.length != 4) {
        throw new IllegalArgumentException("Array must be non-null and have exactly 4 elements.");
      }

      this.a = Double.parseDouble(coefficientsAndUserEmail[0]);
      this.b = Double.parseDouble(coefficientsAndUserEmail[1]);
      this.c = Double.parseDouble(coefficientsAndUserEmail[2]);
      this.userEmail = coefficientsAndUserEmail[3];
      return this;
    }

    public final QuadraticFormula build() {
      // Calculate the answers to the quadratic formula
      double discriminant = b * b - 4 * a * c;
      double answer1 = (-b + Math.sqrt(discriminant)) / (2 * a);
      double answer2 = (-b - Math.sqrt(discriminant)) / (2 * a);

      // Log the calculated answers
      LOGGER.info("Calculated answers: answer1=" + answer1 + ", answer2=" + answer2);

      // Return the QuadraticFormula record with coefficients included
      return new QuadraticFormula(userEmail, a, b, c, answer1, answer2);
    }
  }
}
