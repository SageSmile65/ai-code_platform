package com.ai.code.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "problems")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "difficulty")
    private String difficulty;

    @OneToMany(mappedBy = "problem")
    private Set<Submission> submissions = new LinkedHashSet<>();

    @OneToMany(mappedBy = "problem")
    private Set<TestCase> testCases = new LinkedHashSet<>();
}