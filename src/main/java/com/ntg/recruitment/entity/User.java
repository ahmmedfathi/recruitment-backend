package com.ntg.recruitment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String email;

    private String username;
     private  String fullName;
    private  String phoneNumber;

    private Date birthDate;
    private String University;
    private String faculty;
    private String department;
    private int graduationYear;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;



    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserPosition> userPositions ;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<UserProcess> user_Processes;



}


