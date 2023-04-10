package com.electricitysystem.service;

import com.electricitysystem.entity.InvoiceEntity;
import com.electricitysystem.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<InvoiceEntity> getByCustomerId(int customerId) {
        return invoiceRepository.getAllByCustomerId(customerId);
    }
}
