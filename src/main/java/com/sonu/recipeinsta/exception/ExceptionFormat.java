package com.sonu.recipeinsta.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionFormat {
    private String errorStatusCode;
    private String description;
    private String cause;
}
