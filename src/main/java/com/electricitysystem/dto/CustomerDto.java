package com.electricitysystem.dto;

import lombok.Data;
import org.springframework.data.annotation.ReadOnlyProperty;
@Data
public class CustomerDto {
    String username;
    String verificationCode;

    String password;
    @ReadOnlyProperty
    String name;
    @ReadOnlyProperty
    String address;
    @ReadOnlyProperty
    String phoneNumber;
    @ReadOnlyProperty
    String email;
    @ReadOnlyProperty
    String gender;
}
