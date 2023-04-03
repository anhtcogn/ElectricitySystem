package com.electricitysystem.service;

import com.electricitysystem.entity.CustomerEntity;

import java.util.List;

public interface CustomerService {
    CustomerEntity getCustomerById(int id);

    CustomerEntity getCustomerByUsername(String username);

    CustomerEntity addCustomer(CustomerEntity customer);

    List<CustomerEntity> findAll();
}
