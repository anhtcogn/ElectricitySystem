package com.electricitysystem.controller;

import com.electricitysystem.entity.ElectricBoardEntity;
import com.electricitysystem.service.CustomerService;
import com.electricitysystem.service.ElectricBoardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(ElectricBoardController.class)
public class ElectricBoardControllerTest {

    @InjectMocks
    private ElectricBoardController electricBoardController;

    @Mock
    private ElectricBoardService electricBoardService;

    @Test
    public void testCreateWithValidFile() throws IOException {
        byte[] fileContent = "Test file content".getBytes();
        MultipartFile file = new MockMultipartFile("file", "electric.xlsx", "text/plain", fileContent);
        doNothing().when(electricBoardService).create(file);
        String response = electricBoardController.create(file);
        assertEquals("Import file thành công", response);
    }

    @Test(expected = IOException.class)
    public void testCreateWithInvalidFile() throws Exception {
        byte[] fileContent = "Test file content".getBytes();
        MultipartFile file = new MockMultipartFile("file", "electric.xlsx", "text/plain", fileContent);
        doThrow(new IOException()).when(electricBoardService).create(file);
        String response = electricBoardController.create(file);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithNullFile() throws Exception{
        doThrow(new IllegalArgumentException()).when(electricBoardService).create(null);
        String response = electricBoardController.create(null);
    }

    @Test
    public void testUpdateWithValidInput_ReturnInformation() {
        ElectricBoardEntity board = new ElectricBoardEntity("PAC001",1530,1686,
                "04-05-2023", "HD11300001", "04-2023");
        when(electricBoardService.update(any(ElectricBoardEntity.class)))
                .thenReturn(board);

        ElectricBoardEntity updateBoard = electricBoardController.update(board);
        verify(electricBoardService, times(1)).update(board);
        assertEquals(board, updateBoard);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateWithNullInput(){
        doThrow(new IllegalArgumentException()).when(electricBoardService).update(null);
        electricBoardController.update(null);
    }

    //test with invalid number with non number character
    @Test
    public void testUpdateWithMissingMeterCode_ReturnMessage(){

    }


    @Test
    public void getOneById() {
    }

    @Test
    public void getAllByUsername() {
    }
}