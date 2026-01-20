package com.ntg.recruitment.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class UserDto {
//private   long id;
    private String email;
   // @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;
 //@NotBlank(message = "full name is required")
// @Size(max = 50)
// private String fullName;

    //@NotBlank(message = "University is required")
    private String university;
    private String faculty;
    private String department;
    //@NotNull(message = "Date of birth is required")
    private Date birthDate;
    private Integer graduationYear;
    private  String phoneNumber;

//    @DecimalMin(value = "0.0", inclusive = false, message = "GPA must be greater than 0")
//    @DecimalMax(value = "4.0", message = "GPA must be at most 4.0")
//    private Float GPA;


    //@NotNull(message = "Military status is required")
//    private MilitaryStatus militaryStatus;

   // @NotBlank(message = "Password is required")
//    @Size(min = 6, message = "Password must be at least 6 characters")
//    private String password;



//    //@NotBlank(message = "Job title is required")
//    private String jobTitle;

}
