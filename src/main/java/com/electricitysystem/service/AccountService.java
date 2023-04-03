package com.electricitysystem.service;

import com.electricitysystem.entity.AccountEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface AccountService extends UserDetailsService {
    AccountEntity getAccountEntityByUserName (String username);
    List<AccountEntity> getAccountEntityByRole(int role);
    AccountEntity createAccount(AccountEntity account);
    AccountEntity updateAccount(AccountEntity account);


}
