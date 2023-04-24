package com.electricitysystem.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
@DisplayName("InvoiceRepository Tests")
@Rollback
class InvoiceRepositoryTest {

    @Test
    void tesFindAllByUsernameWithNotNullExpected_ReturnCorrectSize(){

    }

    @Test
    void getAllByStatus() {
    }

    @Test
    void getById() {
    }

    @Test
    void findByToken() {
    }

    @Test
    void findByUsernameAndStatus() {
    }
}