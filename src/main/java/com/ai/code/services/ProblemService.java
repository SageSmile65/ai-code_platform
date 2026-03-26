package com.ai.code.services;

import com.ai.code.entities.Problem;
import com.ai.code.exceptions.ProblemNotFoundException;
import com.ai.code.repositories.ProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProblemService {

    private final ProblemRepository problemRepository;

    public List<Problem> getAllProblems() {
        return StreamSupport.stream(problemRepository.findAll().spliterator(), false)
                .toList();
    }

    public Problem getProblemById(Long id) {
        if (id == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id is required");
        }

        return problemRepository.findById(id)
                .orElseThrow(() -> new ProblemNotFoundException("Problem does not exist"));
    }
}
