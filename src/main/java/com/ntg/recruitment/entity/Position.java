package com.ntg.recruitment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.List;

@Entity
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Position name cannot be blank")
    @Size(max = 100, message = "Position name cannot exceed 100 characters")
    private String name;

    @Min(value = 0, message = "Experience must be 0 or more")
    private int experience;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    @NotBlank(message = "Location cannot be blank")
    @Size(max = 100, message = "Location cannot exceed 100 characters")
    private String location;

    @ManyToMany
    @JoinTable(
            name = "user_position",
            joinColumns = @JoinColumn(name = "position_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> users;
}

