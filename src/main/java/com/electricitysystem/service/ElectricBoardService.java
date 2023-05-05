package com.electricitysystem.service;

import com.electricitysystem.entity.ElectricBoardEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface ElectricBoardService {

    public void create(MultipartFile file) throws IOException;
    public ElectricBoardEntity update(ElectricBoardEntity electricBoard);

    public List<ElectricBoardEntity> getAllByCustomerUserName(String username);
    public ElectricBoardEntity getOneById(Integer electricBoardId);

}
