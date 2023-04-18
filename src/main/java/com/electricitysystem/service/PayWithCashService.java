package com.electricitysystem.service;

import com.electricitysystem.entity.ElectricBoardEntity;
import com.electricitysystem.entity.InvoiceEntity;
import com.electricitysystem.repository.CustomerRepository;
import com.electricitysystem.repository.ElectricBoardRepository;
import com.electricitysystem.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.SecondaryTable;
import java.sql.Time;
import java.time.LocalDateTime;

@Service
public class PayWithCashService {
    @Autowired
    private ElectricBoardRepository electricBoardRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;
    public String pay(int electricBoardId) {
        ElectricBoardEntity electricBoard = electricBoardRepository.getById(electricBoardId);
        InvoiceEntity invoice = new InvoiceEntity();
        invoice.setId(electricBoard.getId());
        invoice.setElectricNumber(electricBoard.getTotalNumber());
        invoice.setCustomerCode(customerRepository.getCustomerEntityById(
                electricBoard.getCustomerCode()).getName());
        invoice.setCustomerName(electricBoard.getCustomerCode());
        invoice.setTotalPayment(electricBoard.getTotalPayment());
        invoice.setStatus("PAID");
        invoice.setElectricNumber(electricBoard.getTotalNumber());
        invoice.setElectricBoardId(electricBoardId);
        invoice.setPaymentDate(LocalDateTime.now());
        invoiceRepository.save(invoice);
        return "Success";
    }
}
