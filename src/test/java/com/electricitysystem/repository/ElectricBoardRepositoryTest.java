package com.electricitysystem.repository;

import com.electricitysystem.entity.ElectricBoardEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("ElectricBoardRepository Tests")
@Rollback
public class ElectricBoardRepositoryTest {

    @Autowired
    ElectricBoardRepository electricBoardRepository;

    @Test
    void testFindAllByUsernameWithNotNullExpectedResult_ReturnCorrectListSize() {
        String username = "HD11300001";
        List<ElectricBoardEntity> list = electricBoardRepository.findAllByUsername(username);
        Assertions.assertThat(list.size()).isNotNull();
        Assertions.assertThat(list.size()).isEqualTo(1);
    }

    @Test
    void testFindAllByUsernameWithNullExpectedResult_ReturnNull() {
        String username = "HD11300002";
        List<ElectricBoardEntity> list = electricBoardRepository.findAllByUsername(username);
        Assertions.assertThat(list.size()).isEqualTo(0);
    }

    @Test
    void testFindElectricBoardByIdWithExistedId_ReturnCorrect() {
        int id = 1;
        ElectricBoardEntity electricBoard = electricBoardRepository.findElectricBoardById(id);
        Assertions.assertThat(electricBoard).isNotNull();
        Assertions.assertThat(electricBoard.getId()).isEqualTo(id);
    }

    @Test
    void testFindElectricBoardByIdWithNotExistedId_ReturnNull() {
        int id = 100;
        ElectricBoardEntity electricBoard = electricBoardRepository.findElectricBoardById(id);
        Assertions.assertThat(electricBoard).isNull();
    }
}