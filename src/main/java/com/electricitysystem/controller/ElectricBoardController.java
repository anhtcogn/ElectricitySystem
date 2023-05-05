package com.electricitysystem.controller;

import com.electricitysystem.entity.ElectricBoardEntity;
import com.electricitysystem.service.ElectricBoardService;
import net.bytebuddy.implementation.auxiliary.AuxiliaryType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/board")
public class ElectricBoardController {

    @Autowired
    private ElectricBoardService electricBoardService;

    @PostMapping(value = "/create",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE )
    public List<ElectricBoardEntity> create(
            @RequestParam("file") MultipartFile files) throws IOException {
        return electricBoardService.create(files);
    }
    @PostMapping("/update")
    public ElectricBoardEntity update(
            @ModelAttribute ElectricBoardEntity electricBoard
    ) {
        return electricBoardService.update(electricBoard);
    }

    @GetMapping("/getOneById")
    public ElectricBoardEntity getOneById(
            @RequestParam int electricBoardId
    ) {
        return electricBoardService.getOneById(electricBoardId);
    }

    @GetMapping("/getAllByUsername")
    public List<ElectricBoardEntity> getAllByUsername(
            @RequestParam String username
    ) {
        return electricBoardService.getAllByCustomerUserName(username);
    }

}
