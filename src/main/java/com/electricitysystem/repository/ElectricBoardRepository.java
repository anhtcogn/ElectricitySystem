package com.electricitysystem.repository;

import com.electricitysystem.entity.ElectricBoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ElectricBoardRepository extends JpaRepository<ElectricBoardEntity, Integer> {
    List<ElectricBoardEntity> findAllByUsername(String customerCode);
    ElectricBoardEntity findElectricBoardById(Integer electricBoardId);
    @Query(value = "select * from electric_board as e where e.username=:username order by time_update desc limit 1", nativeQuery = true)
    ElectricBoardEntity findNearestElectricBoard(
            @Param("username") String username
    );
}

