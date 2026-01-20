package com.ntg.recruitment.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int experience;
    private String description;
    private String location;
    private String reportsTo;

    private LocalDate createdAt;
    private LocalDate expireAt;

    private List<String> skills;

    private List<String> responsibilities;

}
