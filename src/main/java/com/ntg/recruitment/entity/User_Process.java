package com.ntg.recruitment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "process_id"}))
public class User_Process {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "process_id", nullable = false)
    private Process process;

    @Min(value = 0, message = "Grade must be at least 0")
    @Max(value = 100, message = "Grade must be at most 100")
    private int grade;

    @NotBlank(message = "Feedback cannot be blank")
    @Size(max = 500, message = "Feedback cannot exceed 500 characters")
    private String feedback;
}
