package com.ntg.recruitment.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AddCandidatesToProcessDto {


    private String name;
    private Long positionId;
    private List<Long> userIds = new ArrayList<>();
    @Pattern(
            regexp = "^(http|https)://.*$",
            message = "Exam link must be a valid URL (http or https)"
    )
    private String examLink;
}

