package com.sonu.recipeinsta.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.ZonedDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_full_name", nullable = false)
    private String userFullName;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "email_id", nullable = false)
    private String emailId;

    @Column(name = "password", nullable = false)
    private String password;

    @Column( insertable = false)
    @CreatedDate
    private ZonedDateTime createdDateTime;

    @Column(insertable = false)
    @LastModifiedDate
    private ZonedDateTime updatedDateTime;

}
