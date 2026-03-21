package com.ai.code.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "submissions")
public class Submission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @Lob
    @Column(name = "code")
    private String code;

    @Column(name = "language")
    private String language;

    @Lob
    @Column(name = "output")
    private String output;

    @Lob
    @Column(name = "error")
    private String error;

    @Lob
    @Column(name = "ai_feedback")
    private String aiFeedback;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;


}