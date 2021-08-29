package com.example.codingchallenge7principles.address.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AgeDistroDto {
    private String ageRange;
    private Long count;
}
