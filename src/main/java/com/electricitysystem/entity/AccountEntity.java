package com.electricitysystem.entity;

import javax.persistence.*;
import lombok.Data;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

@Entity
@Data
@Table (name = "account")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String username;
    private String password;
    private int role;

    public String hashPassword(String password){
        int salt=10;
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(salt, new SecureRandom());
        return bCryptPasswordEncoder.encode(password);
    }
}

