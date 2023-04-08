package com.electricitysystem.service;

import com.electricitysystem.entity.CalculatorEntity;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public double calculator(int totalNumber) {
        double totalAmount;
        CalculatorEntity calculatorEntity = new CalculatorEntity();

        if (totalNumber <= 50) {
            calculatorEntity.setNumLevel1(totalNumber);
        } else if (totalNumber <= 100) {
            calculatorEntity.setNumLevel1(50);
            calculatorEntity.setNumLevel2(totalNumber - 50);
        } else if (totalNumber <= 200) {
            calculatorEntity.setNumLevel1(50);
            calculatorEntity.setNumLevel2(50);
            calculatorEntity.setNumLevel3(totalNumber - 100);
        } else if (totalNumber <= 300) {
            calculatorEntity.setNumLevel1(50);
            calculatorEntity.setNumLevel2(50);
            calculatorEntity.setNumLevel3(100);
            calculatorEntity.setNumLevel4(totalNumber - 200);
        } else if (totalNumber <= 400) {
            calculatorEntity.setNumLevel1(50);
            calculatorEntity.setNumLevel2(50);
            calculatorEntity.setNumLevel3(100);
            calculatorEntity.setNumLevel4(100);
            calculatorEntity.setNumLevel5(totalNumber - 300);
        } else {
            calculatorEntity.setNumLevel1(50);
            calculatorEntity.setNumLevel2(50);
            calculatorEntity.setNumLevel3(100);
            calculatorEntity.setNumLevel4(100);
            calculatorEntity.setNumLevel5(100);
            calculatorEntity.setNumLevel6(totalNumber - 400);
        }
        totalAmount = calculatorEntity.getNumLevel1() * 1678 + calculatorEntity.getNumLevel2() * 1734
                + calculatorEntity.getNumLevel3() * 2014 + calculatorEntity.getNumLevel4() * 2536
                + calculatorEntity.getNumLevel5() * 2834 + calculatorEntity.getNumLevel6() * 2927;

        double vatAmount = totalAmount * 0.1;
        return totalAmount + vatAmount;
    }
}
