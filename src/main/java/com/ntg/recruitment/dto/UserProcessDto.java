package com.ntg.recruitment.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
public class UserProcessDto {


    private Long userId;

    private Long processId;

    @Min(0)
    @Max(100)
    private Integer grade;

    @Size(max = 500)
    private String feedback;
    @Nullable
    private Long statusId;
//
//    @Pattern(
//            regexp = "^(http|https)://.*$",
//            message = "Exam link must be a valid URL (http or https)"
//    )
    private String examLink;
}
