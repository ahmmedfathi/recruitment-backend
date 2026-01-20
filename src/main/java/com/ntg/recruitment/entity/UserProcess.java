package com.ntg.recruitment.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.Data;


@Entity
@Data
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "process_id"}))
public class UserProcess {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "process_id", nullable = false)
    private RecruitProcess recruitProcess;



    private int grade;


    private String feedback;
    @Column(name = "examLink")
    @Pattern(
            regexp = "^(http|https)://.*$",
            message = "Exam link must be a valid URL (http or https)"
    )
    private String examLink;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    @JsonIgnore
    private Status status;

}
