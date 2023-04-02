package com.electricitysystem.jwt;

import org.springframework.http.HttpStatus;

public class JwtResponse {
    private int id;
    private String token;
    private String type = "Bearer";
    private String username;

    public JwtResponse(String accessToken, int id, String username) {
        this.token = accessToken;
        this.username = username;
        this.id = id;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}