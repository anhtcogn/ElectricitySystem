package com.electricitysystem.service;

import com.electricitysystem.entity.ElectricBoardEntity;

import java.util.List;

public interface ElectricBoardService {

    ElectricBoardEntity create(ElectricBoardEntity electricBoard);
    ElectricBoardEntity update(ElectricBoardEntity electricBoard);

    List<ElectricBoardEntity> getAllByCustomerUserName(String username);
    ElectricBoardEntity getOneById(Integer electricBoardId);

}
