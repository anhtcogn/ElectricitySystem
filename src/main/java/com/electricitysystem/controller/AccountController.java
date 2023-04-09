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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
        String regexLength = "^.{8,20}$";
        if (accountDto.getUsername().trim() == null)
            return ResponseEntity.ok("Vui lòng nhập tên đăng nhập");
        if (accountDto.getPassword().trim() == null)
            return ResponseEntity.ok("Vui lòng nhập mật khẩu");
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

    @PutMapping(value = "/changepassword/{username}",  produces = "application/json")
    public String changePassword(@PathVariable String username,
                                 @RequestParam("oldpassword")String oldpassword,@RequestParam("newpassword")String password){

        if (oldpassword.trim() == null)
            return "Vui lòng nhập mật khẩu cũ";
        if (password.trim() ==null)
            return "Vui lòng nhập mật khẩu mới";
        //validate password : ít nhất 1 số, 1 ký tự viết thường, 1 viết hoa, từ 8-20
        String regexNumber = "^(.*[0-9].*)$";
        String regexLowerCharacter = "^(.*[a-z].*)$";
        String regexUpperCharacter = "^(.*[A-Z].*)$";
        String regexSpecialChars = "^(.*[@,#,$,%,.,,,!,*,&,^,?].*$)";
        String regexLength = "^.{8,20}$";
        if ( !isValidPassword(password, regexLength))
            return "Mật khẩu phải có độ dài từ 8 đến 20 ký tự";
        if ( !isValidPassword(password,regexUpperCharacter))
            return "Mật khẩu phải có ít nhất một chữ hoa";
        if ( !isValidPassword(password, regexNumber))
            return "Mật khẩu phải có ít nhất một ký tự số";
        if ( !isValidPassword(password, regexLowerCharacter))
            return "Mật khẩu phải có ít nhất một chữ thường";
        if (isValidPassword(password, regexSpecialChars))
            return "Mật khẩu không chứa các ký tự đặc biệt";

        AccountEntity account = accountRepository.getAccountEntityByUsername(username);
        if ( account != null){
            if(!bCryptPasswordEncoder.matches(oldpassword, account.getPassword())){
                return  "Mật khẩu cũ không đúng";
            }
            account.setPassword(bCryptPasswordEncoder.encode(password));
            accountRepository.save(account);
            return "Đổi mật khẩu thành công";
        }
        return "Không tìm thấy người dùng";
    }

    private boolean isValidPassword(String password, String regex){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }








}
