package com.electricitysystem.controller;

import com.electricitysystem.entity.ElectricBoardEntity;
import com.electricitysystem.service.ElectricBoardService;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class ElectricBoardController {

    @Autowired
    private ElectricBoardService electricBoardService;

    @PostMapping("/create")
    public ElectricBoardEntity create(
            @ModelAttribute ElectricBoardEntity electricBoard
    ) {
        return electricBoardService.create(electricBoard);
    }
    @PostMapping("/update")
    public ElectricBoardEntity update(
            @ModelAttribute ElectricBoardEntity electricBoard
    ) {
        return electricBoardService.create(electricBoard);
    }

    @GetMapping("/getOneById")
    public ElectricBoardEntity getOneById(
            @RequestParam int electricBoardId
    ) {
        return electricBoardService.getOneById(electricBoardId);
    }

    @GetMapping("/getAllByCustomerId")
    public List<ElectricBoardEntity> getAllByCustomerCode(
            @RequestParam String customerCode
    ) {
        return electricBoardService.getAllByCustomerCode(customerCode);
    }

}
