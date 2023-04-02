package com.electricitysystem.controller;

import com.electricitysystem.dto.AccountDto;
import com.electricitysystem.dto.CustomerDto;
import com.electricitysystem.entity.AccountEntity;
import com.electricitysystem.entity.CustomerEntity;
import com.electricitysystem.jwt.JwtResponse;
import com.electricitysystem.jwt.JwtService;
import com.electricitysystem.repository.AccountRepository;
import com.electricitysystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountService accountService;

    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @PostMapping(value = "login/", consumes = {"multipart/form-data"})
    public ResponseEntity<?> login(@ModelAttribute AccountDto accountDto) throws  Exception{
        try{
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(accountDto.getUsername(), accountDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwt = jwtService.generateToken(userDetails);
            AccountEntity account = accountService.getAccountEntityByUserName(accountDto.getUsername());
            return new ResponseEntity<Object>(new JwtResponse(jwt, account.getId(), userDetails.getUsername()), HttpStatus.OK);
        }catch (Exception e){
            throw new Exception("Authentication Fail");
        }
    }

    @GetMapping(value = "")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok("Hello World");
    }
//
//    @PostMapping(value = "register", consumes = {multipart/form-data})
//    public ResponseEntity<?> register(@ModelAttribute CustomerDto customerDto){
//        try{
//            AccountEntity account = new AccountEntity();
//            account.setUsername(customerDto.getUsername());
//            account.setPassword(customerDto.getPassword());
//            accountService.createAccount(account);
////            CustomerEntity customer = customerService.findOneById(customerDto.getUsername());
//            return ResponseEntity.ok();
//        }
//    }



}
