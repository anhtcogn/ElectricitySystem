package com.electricitysystem.controller;
//
//import com.electricitysystem.dto.AccountDto;
//import com.electricitysystem.entity.AccountEntity;
//import com.electricitysystem.entity.CustomerEntity;
//import com.electricitysystem.jwt.JwtResponse;
//import com.electricitysystem.jwt.JwtUtility;
//import com.electricitysystem.repository.AccountRepository;
//import com.electricitysystem.service.AccountService;
//import com.electricitysystem.service.CustomerService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.ApplicationContext;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//
//import javax.transaction.Transactional;
//import java.util.Arrays;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(AccountController.class)
public class AccountControllerTest {

//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private AccountService accountService;
//
//    @MockBean
//    private CustomerService customerService;
//
//    @MockBean
//    private JwtUtility jwtUtility;
//
//    @Test
//    void testGenerateToken(){
//        String jwt = jwtUtility.generateJwtToken("HD11300001");
//        Assertions.assertNotNull(jwt);
//    }
//
//    @Test
//    void testLoginSuccessWithRoleUserActive_ReturnJwtReponse() throws Exception {
//
//        CustomerEntity customer = new CustomerEntity("HD11300001", "Hoàng Vân Anh", "10 Trần Phú, Mộ Lao, Hà Đông", "0961082342",
//                                                    "hoangvananh7201@gmail.com", 1, "PAC001", "ACTIVE", false);
//
//
//        AccountEntity account = new AccountEntity(1,"HD11300001","$2a$12$lqvL7eCoNwE.Kwb47qaLJO0Y6mWzw9KOt8IxDxMib3vUpmnp39pJy",1, customer, null);
//
//        AccountDto input = new AccountDto("HD11300001", "Password123");
//
//        when(accountService.login(input))
//                .thenReturn(account);
//
//        mockMvc.perform(
//                post("/signin")
//                        .contentType(MediaType.MULTIPART_FORM_DATA)
//                        .flashAttr("account", input))
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.jwt").isNotEmpty())
//                .andExpect(jsonPath("$.id").value(account.getId()))
//                .andExpect(jsonPath("$.username").value(account.getUsername()))
//                .andExpect(jsonPath("$.roles").value("ROLE_USER"))
//                .andExpect(jsonPath("$.code").value(account.getUsername()));
////                .andExpect(jsonPath("$.status").value(customer.getStatus()));
//    }
//    @Test
//    void changePassword() {
//    }
}