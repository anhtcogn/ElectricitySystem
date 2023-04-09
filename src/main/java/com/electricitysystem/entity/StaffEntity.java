package com.electricitysystem.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

@Entity
@Data
@Table(name = "staff")
public class StaffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(nullable = false, unique = true, length = 10)
    private String username;
    private Integer id;
    private String name;
    private String address;
    @Column(name = "phone_number")
    private String phoneNumber;
    private String email;
    private String gender;

}
