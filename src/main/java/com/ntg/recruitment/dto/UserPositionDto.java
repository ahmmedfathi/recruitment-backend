package com.ntg.recruitment.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserPositionDto {

    private Long userId;
    private Long positionId;

    @NotBlank
    @Size(min = 2, max = 100)
    @Pattern(regexp = "^[a-zA-Z\\u0600-\\u06FF\\s]*$", message = "City must contain only letters")
    private String city;

    @NotBlank @Size(min = 3, max = 100)
    private String referenceName;

    @NotBlank @Email
    private String referenceEmail;

    @NotBlank @Pattern(regexp = "^(http|https)://.*$")
    private String cvUrl;

    @NotNull @PastOrPresent
    private Date startDate;

    @NotNull @PastOrPresent
    private Date endDate;

    @NotBlank @Size(min = 2, max = 100)
    private String jobTitle;

    @NotBlank @Size(min = 2, max = 150)
    private String companyName;

    @NotBlank @Size(min = 10, max = 1000)
    private String keyResponsibilities;

//    public UserPositionDto(Long userId, Long positionId, String city, String referenceName, String referenceEmail,
//                           String cvUrl, Date startDate, Date endDate, String jobTitle, String companyName, String keyResponsibilities) {
//        this.userId = userId;
//        this.positionId = positionId;
//        this.city = city;
//        this.referenceName = referenceName;
//        this.referenceEmail = referenceEmail;
//        this.cvUrl = cvUrl;
//        this.startDate = startDate;
//        this.endDate = endDate;
//        this.jobTitle = jobTitle;
//        this.companyName = companyName;
//        this.keyResponsibilities = keyResponsibilities;
//    }
}
