package com.james2ch9developer.pdp_math.repository;

import com.james2ch9developer.pdp_math.document.QuadraticFormula;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuadraticFormulaRepository extends MongoRepository<QuadraticFormula, String> {
}
