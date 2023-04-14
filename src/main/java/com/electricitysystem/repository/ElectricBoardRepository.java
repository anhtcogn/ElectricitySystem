package com.electricitysystem.repository;

import com.electricitysystem.entity.ElectricBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectricBoardRepository extends JpaRepository<ElectricBoardEntity, Integer> {
    List<ElectricBoardEntity> findAllByCustomerId(String customerId);
    ElectricBoardEntity findElectricBoardById(Integer electricBoardId);
}

