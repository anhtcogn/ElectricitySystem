package com.electricitysystem.service;

import com.electricitysystem.entity.ElectricBoardEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ElectricBoardService {

    List<ElectricBoardEntity> create(MultipartFile file) throws IOException;
    ElectricBoardEntity update(ElectricBoardEntity electricBoard);

    List<ElectricBoardEntity> getAllByCustomerUserName(String username);
    ElectricBoardEntity getOneById(Integer electricBoardId);

}
