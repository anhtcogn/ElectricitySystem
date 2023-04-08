package com.electricitysystem.service.impl;

import com.electricitysystem.entity.AccountEntity;
import com.electricitysystem.repository.AccountRepository;
import com.electricitysystem.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository accountRepository;
    @Override
    public AccountEntity getAccountEntityByUserName(String username) {
        return accountRepository.getAccountEntityByUsername(username);
    }

    @Override
    public List<AccountEntity> getAccountEntityByRole(int role) {
        return accountRepository.getAccountEntitiesByRole(role);
    }

    @Override
    public AccountEntity createAccount(AccountEntity account) {
        account.setPassword(account.hashPassword(account.getPassword()));
        accountRepository.save(account);
        return null;
    }

    @Override
    public AccountEntity updateAccount(AccountEntity account) {
        return null;
    }



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountEntity account = this.getAccountEntityByUserName(username);
        if (account ==  null)
            throw  new UsernameNotFoundException(username);
        Set<GrantedAuthority> roles = new HashSet<>();
        return new User(account.getUsername(), account.getPassword(), roles);
    }
}
