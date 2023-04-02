package com.electricitysystem.entity;

import javax.persistence.*;
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
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    private String gender;
    @Column(name = "verification_code")
    private String verificationCode;
}
