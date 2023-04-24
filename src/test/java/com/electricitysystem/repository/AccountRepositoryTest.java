package com.electricitysystem.repository;

import com.electricitysystem.entity.AccountEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

@SpringBootTest
@DisplayName("AccountRepository Tests")
@Rollback
public class AccountRepositoryTest {


    @Autowired
    AccountRepository accountRepository;


    @Test
    public void testGetAccountByUserWithExistedAccount_ReturnNotNull(){
        String username = "HD11300001";
        AccountEntity account = accountRepository.getAccountEntityByUsername(username);
        Assertions.assertThat(account).isNotNull();
        Assertions.assertThat(account.getUsername()).isEqualTo(username);
    }

    @Test
    public void testGetAccountByUserWithNotExistedAccount_ReturnNull(){
        String username = "nonexisted";
        AccountEntity account = accountRepository.getAccountEntityByUsername(username);
        Assertions.assertThat(account).isNull();
    }





}