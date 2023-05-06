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

    @Autowired(required = false)
    private ElectricBoardRepository electricBoardRepository;
    @Autowired(required = false)
    private InvoiceRepository invoiceRepository;
    @Autowired(required = false)
    private CustomerRepository customerRepository;
    @Autowired(required = false)
    protected CalculatorService calculatorService;

    @Override
    public List<ElectricBoardEntity> create(@RequestParam("file") MultipartFile file) throws IOException {
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
            electricBoard.setAddress(row.getCell(4).getStringCellValue());
            electricBoard.setCustomerName(row.getCell(6).getStringCellValue());
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
            invoice.setCustomerName(i.getCustomerName());
            invoice.setTotalPayment(i.getTotalPayment());
            invoice.setStatus("UNPAID");
            invoice.setAddress(i.getAddress());
            LocalDate nextWeek = new LocalDate().plusDays(7);
            Date date = nextWeek.toDate();
            invoice.setLastTimePay(sdf.format(date));
            invoice.setElectricNumber(i.getTotalNumber());
            invoice.setElectricBoardId(i.getId());
            invoiceRepository.save(invoice);
        }

        for (ElectricBoardEntity i:entities) {
            ElectricBoardEntity electric1 = electricBoardRepository.findNearestElectricBoard(i.getUsername());

            int thisMonth = DateTime.now().getMonthOfYear() - 1;
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
        return electricBoardRepository.saveAll(entities);
    }

    @Override
    public ElectricBoardEntity update(ElectricBoardEntity electricBoard) {
        ElectricBoardEntity entity = electricBoardRepository.findElectricBoardById(electricBoard.getId());
        entity.setNewNumber(electricBoard.getNewNumber());
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
