package com.james2ch9developer.pdp_math.quadratic_formula;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import com.james2ch9developer.pdp_math.document.QuadraticFormula;

public class QuadraticFormulaTest {

  @Test
  public void testCalculate() {
    final String[] coefficientsAndUserEmail = {"1", "4", "3", "john.doe@yahoo.com"};

    QuadraticFormula quadraticFormulaData = new QuadraticFormula.Builder()
        .setCoefficientsAndUserEmail(coefficientsAndUserEmail).build();

    assertEquals(-1.0, quadraticFormulaData.getAnswer1());
    assertEquals(-3.0, quadraticFormulaData.getAnswer2());
  }
}
