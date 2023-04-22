package com.electricitysystem.service.impl;

import com.electricitysystem.entity.ElectricBoardEntity;
import com.electricitysystem.entity.InvoiceEntity;
import com.electricitysystem.repository.CustomerRepository;
import com.electricitysystem.repository.ElectricBoardRepository;
import com.electricitysystem.repository.InvoiceRepository;
import com.electricitysystem.service.CalculatorService;
import com.electricitysystem.service.ElectricBoardService;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ElectricBoardServiceImpl implements ElectricBoardService {

    @Autowired
    private ElectricBoardRepository electricBoardRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    protected CalculatorService calculatorService;
    @Override
    public ElectricBoardEntity create(ElectricBoardEntity electricBoard) {
        ElectricBoardEntity entity = new ElectricBoardEntity();
        entity.setUsername(electricBoard.getUsername());
        entity.setMeterCode(customerRepository.getByUsername(electricBoard.getUsername()).getMeterCode());
        entity.setOldNumber(electricBoard.getOldNumber());
        entity.setNewNumber(electricBoard.getNewNumber());
        entity.setTimeReadMeter(electricBoard.getTimeReadMeter());
        entity.setTimeUpdate(LocalDateTime.now());
        entity.setPeriod(electricBoard.getPeriod());
        electricBoardRepository.save(entity);
        entity.setTotalNumber(entity.getNewNumber() - entity.getOldNumber());
        entity.setTotalPayment(calculatorService.calculator(entity.getTotalNumber()));
        electricBoardRepository.save(entity);
        InvoiceEntity invoice = new InvoiceEntity();
        invoice.setId(entity.getId());
        invoice.setElectricNumber(entity.getTotalNumber());
        invoice.setUsername(entity.getUsername());
        invoice.setCustomerName(customerRepository.getByUsername(electricBoard.getUsername()).getName());
        invoice.setTotalPayment(entity.getTotalPayment());
        invoice.setStatus("UNPAID");

        LocalDate nextWeek = new LocalDate().minusDays(1);
        Date date = nextWeek.toDate();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        invoice.setLastTimePay(sdf.format(date));

        invoice.setElectricNumber(entity.getTotalNumber());
        invoice.setElectricBoardId(entity.getId());
        invoiceRepository.save(invoice);

        return electricBoardRepository.save(entity);
    }

    @Override
    public ElectricBoardEntity update(ElectricBoardEntity electricBoard) {
        return electricBoardRepository.save(electricBoard);
    }

    @Override
    public List<ElectricBoardEntity> getAllByCustomerUserName(String username) {
        return electricBoardRepository.findAllByUsername(username);
    }

    @Override
    public ElectricBoardEntity getOneById(Integer electricBoardId) {
        return electricBoardRepository.findElectricBoardById(electricBoardId);
    }
}
