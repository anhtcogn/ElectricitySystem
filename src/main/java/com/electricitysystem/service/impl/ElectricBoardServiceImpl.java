package com.electricitysystem.service.impl;

import com.electricitysystem.entity.ElectricBoardEntity;
import com.electricitysystem.repository.CustomerRepository;
import com.electricitysystem.repository.ElectricBoardRepository;
import com.electricitysystem.service.CalculatorService;
import com.electricitysystem.service.ElectricBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ElectricBoardServiceImpl implements ElectricBoardService {

    @Autowired
    private ElectricBoardRepository electricBoardRepository;

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    protected CalculatorService calculatorService;
    @Override
    public ElectricBoardEntity create(ElectricBoardEntity electricBoard) {
        ElectricBoardEntity entity = new ElectricBoardEntity();
        entity.setCustomerCode(electricBoard.getCustomerCode());
        entity.setMeterCode(customerRepository.getCustomerEntityById(electricBoard.getCustomerCode()).getMeterCode());
        entity.setOldNumber(electricBoard.getOldNumber());
        entity.setNewNumber(electricBoard.getNewNumber());
        entity.setTimeReadMeter(electricBoard.getTimeReadMeter());
        entity.setTimeUpdate(LocalDateTime.now());
        entity.setPeriod(electricBoard.getPeriod());
        electricBoardRepository.save(entity);
        entity.setTotalNumber(entity.getNewNumber() - entity.getOldNumber());
        entity.setTotalPayment(calculatorService.calculator(entity.getTotalNumber()));
        return electricBoardRepository.save(entity);
    }

    @Override
    public ElectricBoardEntity update(ElectricBoardEntity electricBoard) {
        return electricBoardRepository.save(electricBoard);
    }

    @Override
    public List<ElectricBoardEntity> getAllByCustomerCode(String customerCode) {
        return electricBoardRepository.findAllByCustomerCode(customerCode);
    }

    @Override
    public ElectricBoardEntity getOneById(Integer electricBoardId) {
        return electricBoardRepository.findElectricBoardById(electricBoardId);
    }
}
