package com.electricitysystem;

import com.electricitysystem.AuthenticationController;
import com.electricitysystem.controller.AccountController;
import com.electricitysystem.dto.AccountDto;
import com.electricitysystem.entity.AccountEntity;
import com.electricitysystem.entity.CustomerEntity;
import com.electricitysystem.entity.StaffEntity;
import com.electricitysystem.jwt.JwtResponse;
import com.electricitysystem.jwt.JwtUtility;
import com.electricitysystem.repository.AccountRepository;
import com.electricitysystem.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthenticationControllerTest {
    @Autowired
    private AccountController accountController;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerService customerService;

    @Test
    public void testAuthenticateUser() {
        // Tạo đối tượng AccountDto để truyền vào phương thức authenticateUser()
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername("admin");
        accountDto.setPassword("123456");

        // Giả lập thông tin đăng nhập hợp lệ
        AuthenticationManager authenticationManager = Mockito.mock(AuthenticationManager.class);
        JwtUtility jwtUtility = Mockito.mock(JwtUtility.class);
        Authentication authentication = Mockito.mock(Authentication.class);
        Mockito.when(authenticationManager.authenticate(Mockito.any()))
                .thenReturn(authentication);
        Mockito.when(jwtUtility.generateJwtToken(Mockito.anyString()))
                .thenReturn("jwt-token");
        ReflectionTestUtils.setField(accountController, "authenticationManager", authenticationManager);
        ReflectionTestUtils.setField(accountController, "jwtUtility", jwtUtility);

        // Giả lập thông tin tài khoản và khách hàng
        AccountEntity account = Mockito.mock(AccountEntity.class);
        StaffEntity staff = Mockito.mock(StaffEntity.class);
        Mockito.when(account.getRole()).thenReturn(0);
        Mockito.when(account.getStaff()).thenReturn(staff);
        Mockito.when(accountRepository.getAccountEntityByUsername(Mockito.anyString()))
                .thenReturn(account);

        // Thiết lập kết quả trả về từ phương thức customerService.updateStatus()
        CustomerEntity customer = Mockito.mock(CustomerEntity.class);
        Mockito.when(customer.getStatus()).thenReturn("PAID");
        Mockito.when(customerService.updateStatus(Mockito.anyString(), Mockito.any()))
                .thenReturn(customer);

        // Gọi phương thức authenticateUser() và kiểm tra kết quả
        ResponseEntity<?> response = accountController.authenticateUser(accountDto);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        JwtResponse jwtResponse = (JwtResponse) response.getBody();
        assertThat(accountDto.getUsername()).isEqualTo("admin123");
        assertThat(accountDto.getPassword()).isEqualTo("Password123");
    }
}
