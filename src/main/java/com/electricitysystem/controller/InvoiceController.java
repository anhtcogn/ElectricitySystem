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

    @GetMapping("/getAllByUsername")
    public List<InvoiceEntity> getAllByUsername(
            @RequestParam String username) {
        return invoiceService.getAllByUsername(username);
    }

    @GetMapping("/getByStatus")
    public List<InvoiceEntity>  getByStatus(
            @RequestParam String status
    ) {
        return invoiceService.getAllByStatus(status);
    }
    @GetMapping("/getAll")
    public List<InvoiceEntity> getAll() {
        return invoiceService.getAll();
    }

    @GetMapping("/getById")
    public InvoiceEntity getById(
            @RequestParam int id
    ) {
        return invoiceService.getById(id);
    }
    @PostMapping("/updateStatus")
    public InvoiceEntity updateStatus(
            @RequestParam int id,
            @RequestParam String status
    ) {
        return invoiceService.updateStatus(id, status);
    }

}

