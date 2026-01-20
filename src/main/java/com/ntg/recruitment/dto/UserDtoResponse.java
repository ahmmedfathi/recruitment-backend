package com.ntg.recruitment.dto;

import com.ntg.recruitment.entity.MilitaryStatus;
import com.ntg.recruitment.entity.Status;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;
@Data
public class UserDtoResponse {
    private long id;
    private String email;
    private String username;
    private  String fullName;
    private  String phoneNumber;
    private Date birthDate;
    private String University;
    private String faculty;
    private String department;
    private int graduationYear;



//    @NotBlank(message = "Job title is required")
//    private String jobTitle;


}
