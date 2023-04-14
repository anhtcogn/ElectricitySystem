package com.electricitysystem.controller;

import com.electricitysystem.entity.InvoiceEntity;
import com.electricitysystem.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping("/getAllByCustomerId")
    public List<InvoiceEntity> getAllByCustomerCode(
            @RequestParam String customerCode) {
        return invoiceService.getByCustomerCode(customerCode);
    }

    @GetMapping("/getAll")
    public List<InvoiceEntity> getAll() {
        return invoiceService.getAll();
    }

}

