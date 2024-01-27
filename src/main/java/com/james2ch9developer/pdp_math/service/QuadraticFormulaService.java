package com.james2ch9developer.pdp_math.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.james2ch9developer.pdp_math.document.QuadraticFormula;
import com.james2ch9developer.pdp_math.repository.QuadraticFormulaRepository;

@Service
public class QuadraticFormulaService {

    @Autowired
    private QuadraticFormulaRepository quadraticFormulaRepository;

    public QuadraticFormula getByEmail(final String email) {
        return quadraticFormulaRepository.findById(email).orElse(null);
    }

    public void save(final QuadraticFormula quadraticFormula) {
        quadraticFormulaRepository.save(quadraticFormula);
    }
}
