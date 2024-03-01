package com.sonu.recipeinsta.dto.requestdto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRquestDto {
    private String userFullName;
    private String dob;
    private String emailId;
    private String password;
}
