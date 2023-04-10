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

    @GetMapping("/getAllByCustomerId/{customerId}")
    public List<InvoiceEntity> getAllByCustomerId(
            @RequestParam("customerId") int customerId) {
        return invoiceService.getByCustomerId(customerId);
    }
}
