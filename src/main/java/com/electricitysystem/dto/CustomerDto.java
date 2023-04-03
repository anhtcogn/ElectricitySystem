package com.electricitysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    String username;
    String name;
    String address;

    String phoneNumber;

    String email;

    int gender;
    String verification_code;
}
