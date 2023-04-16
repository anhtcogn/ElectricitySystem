package com.electricitysystem.jwt;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
    private String token;
    private String type = "Bearer ";
    private Integer id;
    private String username;
    private List<String> roles;

    private String code;

    public JwtResponse(String token, Integer id, String username,
                       List<String> roles, String code) {
        super();
        this.token = token;
        this.id = id;
        this.username = username;
        this.roles = roles;
        this.code=code;
    }


}