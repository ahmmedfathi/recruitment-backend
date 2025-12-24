package com.ntg.recruitment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Process name cannot be blank")
    @Size(min = 3, max = 100, message = "Process name must be between 3 and 100 characters")
    private String name;

    @OneToMany(mappedBy = "process", fetch = FetchType.LAZY)
    private List<User_Process> userProcesses;
}

