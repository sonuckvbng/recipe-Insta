package com.sonu.recipeinsta.Dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {
    private String userFullName;
    private String dob;
    private String emailId;
    private String password;
}
