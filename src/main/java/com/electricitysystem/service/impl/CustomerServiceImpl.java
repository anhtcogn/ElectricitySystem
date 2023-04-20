package com.electricitysystem.service.impl;

import com.electricitysystem.entity.CustomerEntity;
import com.electricitysystem.repository.CustomerRepository;
import com.electricitysystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerEntity getCustomerByUsername(String username) {
        return customerRepository.getByUsername(username);
    }

    @Override
    public CustomerEntity addCustomer(CustomerEntity customer) {

        return customerRepository.save(customer);
    }

    @Override
    public List<CustomerEntity> findAll() {
        return customerRepository.findAll();
    }
}
