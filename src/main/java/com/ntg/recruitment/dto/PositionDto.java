package com.ntg.recruitment.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PositionDto {

    private Long id;
    @NotBlank(message = "Position name cannot be blank")
    @Size(max = 100, message = "Position name cannot exceed 100 characters")
    private String name;

    @Min(value = 0, message = "Experience must be 0 or more")
    private int experience;

    @NotBlank(message = "Description cannot be blank")
    @Size(max = 500, message = "Description cannot exceed 500 characters")
    private String description;

    private String reportsTo;

    @NotBlank(message = "Location cannot be blank")
    @Size(max = 100, message = "Location cannot exceed 100 characters")
    private String location;

    private Date createdAt;
    private Date expireAt;

    private List<String> skills;
    private List<String> responsibilities;
}
