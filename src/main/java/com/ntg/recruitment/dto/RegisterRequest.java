package com.ntg.recruitment.dto;

import com.ntg.recruitment.entity.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {


    @NotBlank(message = "Username cannot be blank")
    @Size(min = 3,max = 20, message = "Username must be between 3 and 20 characters")
    private String username;


    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    @Column(unique = true, nullable = false)
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
//    @Pattern(
//            regexp = "^(?=.*[A-Z])(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$",
//            message = "Password must contain at least one uppercase letter and one special character"
    private String password;

    private Role role = Role.CANDIDATE;

}
