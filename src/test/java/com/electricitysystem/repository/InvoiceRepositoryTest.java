package com.electricitysystem.repository;

import com.electricitysystem.entity.InvoiceEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@DisplayName("InvoiceRepository Tests")
@Rollback
public class InvoiceRepositoryTest {

    @Autowired
    InvoiceRepository invoiceRepository;

    @Test
    void testFindAllByUsernameWithNotNullExpected_ReturnCorrectSize(){
        String username = "HD11300001";
        List<InvoiceEntity> list = invoiceRepository.findAllByUsername(username);
        Assertions.assertNotNull(list);
        Assertions.assertEquals(list.size(), 1);
        for(InvoiceEntity i : list)
            Assertions.assertEquals(i.getUsername(), username);
    }

    @Test
    void testFindAllByUsernameWithNullExpected_ReturnZero(){
        String username = "HD11300002";
        List<InvoiceEntity> list = invoiceRepository.findAllByUsername(username);
        Assertions.assertEquals(list.size(),0);
    }

    @Test
    void testFindAllByUsernameWithNotExistedUsername_ReturnZero(){
        String username = "notexisted";
        List<InvoiceEntity> list = invoiceRepository.findAllByUsername(username);
        Assertions.assertEquals(list.size(),0);
    }

    @Test
    void testGetAllByStatusWithNotNullExpected_ReturnCorrectSize() {
        String status = "UNPAID";
        List<InvoiceEntity> list = invoiceRepository.getAllByStatus(status);
        Assertions.assertNotNull(list);
        Assertions.assertEquals(list.size(), 1);
        for(InvoiceEntity i : list)
            Assertions.assertEquals(i.getStatus(), status);
    }

    @Test
    void testGetAllByStatusWithNullExpected_ReturnZero() {
        String status = "PAID";
        List<InvoiceEntity> list = invoiceRepository.getAllByStatus(status);
        Assertions.assertEquals(list.size(),0);
    }

    @Test
    void testGetAllByStatusWithNotExistedStatus_ReturnZero() {
        String status = "NOTEXISTED";
        List<InvoiceEntity> list = invoiceRepository.getAllByStatus(status);
        Assertions.assertEquals(list.size(),0);
    }

    @Test
    void testGetByIdWithExistedId_ReturnCorrectObject() {
        int id = 1;
        InvoiceEntity invoice = invoiceRepository.getById(id);
        Assertions.assertNotNull(invoice);
        Assertions.assertEquals(invoice.getId(),id);
    }

    @Test
    void testGetByIdWithNotExistedId_ReturnNull() {
        int id = 100;
        InvoiceEntity invoice = invoiceRepository.getById(id);
        Assertions.assertNull(invoice);
    }

    @Test
    void testFindByTokenWithExistedToken_ReturnCorrectObject() {
        //add token
        String token = "stringtoken";
        InvoiceEntity invoice = invoiceRepository.findByToken(token);
        Assertions.assertNotNull(invoice);
        Assertions.assertEquals(invoice.getToken(),token);
    }

    @Test
    void testGetByIdWithNotExistedToken_ReturnNull() {
        String token = "notexistedtoken";
        InvoiceEntity invoice = invoiceRepository.findByToken(token);
        Assertions.assertNull(invoice);
    }

    @Test
    void testFindByUsernameAndStatusWithCorrectInput_ReturnCorrectObject() {
        String username = "HD11300001";
        String status = "UNPAID";
        InvoiceEntity invoice = invoiceRepository.findByUsernameAndStatus(username, status);
        Assertions.assertNotNull(invoice);
        Assertions.assertEquals(invoice.getUsername(),  username);
        Assertions.assertEquals(invoice.getStatus(),  status);
    }

    @Test
    void testFindByUsernameAndStatusWithIncorrectUsername_ReturnCorrectObject() {
        String username = "wrongUsername";
        String status = "UNPAID";
        InvoiceEntity invoice = invoiceRepository.findByUsernameAndStatus(username, status);
        Assertions.assertNull(invoice);
    }

    @Test
    void testFindByUsernameAndStatusWithIncorrectStatus_ReturnCorrectObject() {
        String username = "HD11300001";
        String status = "Incorrect";
        InvoiceEntity invoice = invoiceRepository.findByUsernameAndStatus(username, status);
        Assertions.assertNull(invoice);
    }
}