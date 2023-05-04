package com.electricitysystem.service.impl;

import com.electricitysystem.entity.CustomerEntity;
import com.electricitysystem.entity.ElectricBoardEntity;
import com.electricitysystem.entity.InvoiceEntity;
import com.electricitysystem.repository.CustomerRepository;
import com.electricitysystem.repository.ElectricBoardRepository;
import com.electricitysystem.repository.InvoiceRepository;
import com.electricitysystem.service.CalculatorService;
import com.electricitysystem.service.ElectricBoardService;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

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
//    @Override
//    public ElectricBoardEntity create(ElectricBoardEntity electricBoard) {
//        ElectricBoardEntity entity = new ElectricBoardEntity();
//
//        entity.setUsername(electricBoard.getUsername());
//        entity.setMeterCode(customerRepository.getByUsername(electricBoard.getUsername()).getMeterCode());
//        ElectricBoardEntity entity1 = electricBoardRepository.findNearestElectricBoard(electricBoard.getUsername());
//        if (entity1 != null) {
//            entity.setOldNumber(entity1.getOldNumber());
//        }
//        else entity.setOldNumber(0);
//        entity.setNewNumber(electricBoard.getNewNumber());
//        entity.setTimeUpdate(LocalDateTime.now());
//
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
//        entity.setTimeReadMeter(sdf.format(new LocalDate().toDate()));
//        int thisMon = new Date().getMonth() + 1;
//        String period = thisMon + "/" + "2023";
//        entity.setPeriod(period);
//
//        electricBoardRepository.save(entity);
//        entity.setTotalNumber(entity.getNewNumber() - entity.getOldNumber());
//        entity.setTotalPayment(calculatorService.calculator(entity.getTotalNumber()));
//        electricBoardRepository.save(entity);
//
//        InvoiceEntity invoice = new InvoiceEntity();
//        invoice.setId(entity.getId());
//        invoice.setElectricNumber(entity.getTotalNumber());
//        invoice.setUsername(entity.getUsername());
//        invoice.setCustomerName(customerRepository.getByUsername(electricBoard.getUsername()).getName());
//        invoice.setTotalPayment(entity.getTotalPayment());
//        invoice.setStatus("UNPAID");
//        invoice.setAddress(customerRepository.getByUsername(electricBoard.getUsername()).getAddress());
//        LocalDate nextWeek = new LocalDate().minusDays(2);
//        Date date = nextWeek.toDate();
//        invoice.setLastTimePay(sdf.format(date));
//
//        invoice.setElectricNumber(entity.getTotalNumber());
//        invoice.setElectricBoardId(entity.getId());
//        invoiceRepository.save(invoice);
//
//        return electricBoardRepository.save(entity);
//    }

    @Override
    public void create(@RequestParam("file") MultipartFile file) throws IOException {
        List<ElectricBoardEntity> entities = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());

        // Read data form excel file sheet1.
        XSSFSheet worksheet = workbook.getSheetAt(0);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        for (int index = 1; index < worksheet.getPhysicalNumberOfRows(); index++) {
            XSSFRow row = worksheet.getRow(index);
            ElectricBoardEntity electricBoard = new ElectricBoardEntity();
            electricBoard.setPeriod(row.getCell(1).getStringCellValue());
            electricBoard.setUsername(row.getCell(2).getStringCellValue());
            electricBoard.setMeterCode(row.getCell(3).getStringCellValue());
            electricBoard.setOldNumber((int)(row.getCell(7).getNumericCellValue()));
            electricBoard.setNewNumber((int)(row.getCell(8).getNumericCellValue()));
            electricBoard.setTimeUpdate(LocalDateTime.now());
            electricBoard.setTimeReadMeter(sdf.format(new LocalDate().toDate()));
            int totalNumber = (int)(row.getCell(8).getNumericCellValue()) -
                    (int)(row.getCell(7).getNumericCellValue());
            electricBoard.setTotalNumber(totalNumber);
            electricBoard.setTotalPayment(calculatorService.calculator(totalNumber));
            entities.add(electricBoard);
        }
        electricBoardRepository.saveAll(entities);

        for (ElectricBoardEntity i:entities) {
            InvoiceEntity invoice = new InvoiceEntity();
            invoice.setId(i.getId());
            invoice.setElectricNumber(i.getTotalNumber());
            invoice.setUsername(i.getUsername());
            invoice.setCustomerName(customerRepository.getByUsername(i.getUsername()).getName());
            invoice.setTotalPayment(i.getTotalPayment());
            invoice.setStatus("UNPAID");
            invoice.setAddress(customerRepository.getByUsername(i.getUsername()).getAddress());
            LocalDate nextWeek = new LocalDate().plusDays(7);
            Date date = nextWeek.toDate();
            invoice.setLastTimePay(sdf.format(date));
            invoice.setElectricNumber(i.getTotalNumber());
            invoice.setElectricBoardId(i.getId());
            invoiceRepository.save(invoice);
        }

        for (ElectricBoardEntity i:entities) {
            ElectricBoardEntity electric1 = electricBoardRepository.findNearestElectricBoard(i.getUsername());

            int thisMonth = DateTime.now().getMonthOfYear();
            int thisYear = DateTime.now().getYear();
            String period;
            if (thisMonth < 10) {
                period = "0"+ thisMonth + "-" + thisYear;
            } else {
                period = thisMonth + "-" + thisYear;
            }

            CustomerEntity customer = customerRepository.getByUsername(i.getUsername());
            customer.setCheckUpdate(Objects.equals(electric1.getPeriod(), period));
            customerRepository.save(customer);
        }
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
