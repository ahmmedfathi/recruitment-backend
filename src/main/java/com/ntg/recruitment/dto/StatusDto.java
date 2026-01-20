package com.ntg.recruitment.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class StatusDto {
    @Column(nullable = false, unique = true)
    private String name;
}
