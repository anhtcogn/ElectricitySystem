package com.electricitysystem.controller;

import com.electricitysystem.dto.AccountDetails;
import com.electricitysystem.dto.AccountDto;
import com.electricitysystem.entity.AccountEntity;
import com.electricitysystem.jwt.JwtResponse;
import com.electricitysystem.jwt.JwtUtility;
import com.electricitysystem.repository.AccountRepository;
import com.electricitysystem.service.AccountService;
import com.electricitysystem.service.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AccountController {
    @Autowired
    private JwtUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping(value = "/login", consumes = {"multipart/form-data"})
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtility.generateJwtToken(loginRequest.getUsername());
        AccountDetails userDetails = (AccountDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        return ResponseEntity.ok(
                new JwtResponse(jwt,userDetails.getId(),userDetails.getUsername(),roles)
        );
    }

    @GetMapping(value="/authenticate", produces = "application/json")
    public boolean authenticate(@RequestHeader("Authorization") String token) {
        return jwtUtility.validateJwtToken(token);
    }
//
//    @PostMapping(value = "register", consumes = {"multipart/form-data"})
//    public ResponseEntity<?> register(@ModelAttribute CustomerDto customerDto){
//        try{
//            AccountEntity account = new AccountEntity();
//            account.setUsername(customerDto.getUsername());
//            account.setPassword(customerDto.getPassword());
//            accountService.createAccount(account);
////            CustomerEntity customer = customerService.findOneById(customerDto.getUsername());
//            return ResponseEntity.ok(account);
//        } catch (Exception e){
//            return ResponseEntity.ok("Fail");
//        }
//    }





}
