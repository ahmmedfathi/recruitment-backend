package com.ntg.recruitment.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private long id;
    private String token;
    private String email;
    private String username;
    private String fullName;
    private String phoneNumber;
    private Date birthDate;
    private String University;
    private String faculty;
    private String department;
    private int graduationYear;
    private String role;

}