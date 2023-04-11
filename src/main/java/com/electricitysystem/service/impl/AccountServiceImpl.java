package com.electricitysystem.service.impl;

import com.electricitysystem.entity.AccountEntity;
import com.electricitysystem.entity.CustomerEntity;
import com.electricitysystem.entity.StaffEntity;
import com.electricitysystem.repository.AccountRepository;
import com.electricitysystem.repository.CustomerRepository;
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
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public AccountEntity getAccountEntityByUserName(String username) {
        return accountRepository.getAccountEntityByUsername(username);
    }


    @Override
    public AccountEntity createAccount(AccountEntity account) {
        account.setPassword(account.hashPassword(account.getPassword()));
        accountRepository.save(account);
        return null;
    }


    @Override
    public CustomerEntity getCustomerbyAccount(AccountEntity account) {
        if(account.getCustomer()!=null)
            return account.getCustomer();
        return null;
    }

    @Override
    public StaffEntity getStaffbyAccount(AccountEntity account) {
        if(account.getStaff()!=null)
            return account.getStaff();
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
