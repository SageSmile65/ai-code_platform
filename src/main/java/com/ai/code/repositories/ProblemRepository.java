package com.ai.code.repositories;

import com.ai.code.entities.Problem;
import org.springframework.data.repository.CrudRepository;

public interface ProblemRepository extends CrudRepository<Problem, Long> {
}
