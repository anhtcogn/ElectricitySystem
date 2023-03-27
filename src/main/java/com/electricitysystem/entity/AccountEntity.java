package com.electricitysystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table (name = "account")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private Enum role;
}
