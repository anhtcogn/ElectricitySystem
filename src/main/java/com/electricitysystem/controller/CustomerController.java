package com.electricitysystem.controller;

import com.electricitysystem.dto.CustomerDto;
import com.electricitysystem.entity.CustomerEntity;
import com.electricitysystem.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

//    @PostMapping(value = "addCustomer", consumes = {"multipart/form-data"})
//    public ResponseEntity<?> addCustomer(@ModelAttribute CustomerDto customerDto){
//        CustomerEntity customer = new CustomerEntity();
//        customer.setUsername(customerDto.getUsername());
//        customer.setName(customerDto.getName());
//        customer.setAddress(customerDto.getAddress());
//        customer.setPhoneNumber(customerDto.getPhoneNumber());
//        customer.setEmail(customerDto.getEmail());
//        customer.setGender(customerDto.getGender());
//        customerService.addCustomer(customer);
//        return ResponseEntity.ok(customer);
//    }
    @GetMapping("")
    public ResponseEntity<?> allCustomer(){
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("{username}")
    public ResponseEntity<?> findOneByUsername(@PathVariable String username){
        return ResponseEntity.ok(customerService.getCustomerByUsername(username));
    }
}
