package com.electricitysystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String gender;
    private String meterCode;
    private String verificationCode;
}
