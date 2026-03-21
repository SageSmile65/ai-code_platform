package com.ai.code.controllers;

import com.ai.code.controllers.dto.ProblemDetailsResponse;
import com.ai.code.controllers.dto.ProblemSummaryResponse;
import com.ai.code.entities.Problem;
import com.ai.code.services.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/problems")
@RequiredArgsConstructor
public class ProblemController {

    private final ProblemService problemService;

    @GetMapping
    public ResponseEntity<List<ProblemSummaryResponse>> getAllProblems() {
        List<ProblemSummaryResponse> response = problemService.getAllProblems().stream()
                .map(problem -> new ProblemSummaryResponse(
                        problem.getId(),
                        problem.getTitle(),
                        problem.getDifficulty()
                ))
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProblemDetailsResponse> getProblemById(@PathVariable Long id) {
        Problem problem = problemService.getProblemById(id);

        ProblemDetailsResponse response = new ProblemDetailsResponse(
                problem.getId(),
                problem.getTitle(),
                problem.getDescription(),
                problem.getDifficulty()
        );

        return ResponseEntity.ok(response);
    }
}
