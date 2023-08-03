package com.james2ch9developer.pdp_math.document;

import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.Logger;

public record QuadraticFormula(double answer1, double answer2, double a, double b, double c, String userEmail) {

  private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(QuadraticFormula.class);

  // Method to retrieve the coefficients used in the calculation
  public void printCoefficients() {
    LOGGER.info("Coefficients: a=" + a + ", b=" + b + ", c=" + c);
  }

  // Builder class for QuadraticFormula
  public static class Builder {
    private double a;
    private double b;
    private double c;
    private String userEmail;

    public final Builder setCoefficientsAndUserEmail(final String[] coefficientsAndUserEmail) {

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
      return new QuadraticFormula(answer1, answer2, a, b, c, userEmail);
    }
  }
}
