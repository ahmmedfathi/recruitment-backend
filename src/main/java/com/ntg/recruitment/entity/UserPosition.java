package com.ntg.recruitment.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Table(name = "user_positions")
@Data
public class UserPosition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", nullable = false)
    private Position position;

    private String city;
    private String referenceName;
    private String referenceEmail;
    private String cvUrl;
    private Date startDate;
    private Date endDate;
    private String jobTitle;
    private String companyName;
    private String keyResponsibilities;
}
