package com.electricitysystem.controller;

import com.electricitysystem.service.impl.AccountDetails;
import com.electricitysystem.dto.AccountDto;
import com.electricitysystem.entity.AccountEntity;
import com.electricitysystem.jwt.JwtResponse;
import com.electricitysystem.jwt.JwtUtility;
import com.electricitysystem.repository.AccountRepository;
import com.electricitysystem.repository.CustomerRepository;
import com.electricitysystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    private AccountService accountService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping(value = "/signin", consumes = {"multipart/form-data"})
    public ResponseEntity<?> authenticateUser(@ModelAttribute AccountDto accountDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(accountDto.getUsername(), accountDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtility.generateJwtToken(accountDto.getUsername());
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

    @PutMapping(value = "/changepassword",  produces = "application/json")
    public String changePassword(@RequestBody AccountDto accountDto, @RequestParam("newpassword")String password){
        AccountEntity account = accountRepository.getAccountEntityByUsername(accountDto.getUsername());
        if(!bCryptPasswordEncoder.matches(accountDto.getPassword(), account.getPassword())){
            return  "Mật khẩu cũ không đúng";
        }
        account.setPassword(bCryptPasswordEncoder.encode(password));
        accountRepository.save(account);
        return "Đổi mật khẩu thành công";
    }

}
