package com.electricitysystem.entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "staff")
public class StaffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    private String gender;
}
