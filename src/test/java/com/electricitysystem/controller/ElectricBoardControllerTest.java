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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        ResponseEntity<?> response = electricBoardController.create(file);
        assertEquals("Import file thành công", response.getBody());
    }

    @Test(expected = IOException.class)
    public void testCreateWithInvalidFile() throws Exception {
        byte[] fileContent = "Test file content".getBytes();
        MultipartFile file = new MockMultipartFile("file", "electric.xlsx", "text/plain", fileContent);
        doThrow(new IOException()).when(electricBoardService).create(file);
        ResponseEntity<?> response = electricBoardController.create(file);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateWithNullFile() throws Exception{
        doThrow(new IllegalArgumentException()).when(electricBoardService).create(null);
        ResponseEntity<?> response = electricBoardController.create(null);
    }

    @Test
    public void testUpdateWithValidInput_ReturnInformation() {
        ElectricBoardEntity board = new ElectricBoardEntity("PAC001",1530,1686,
                "04-05-2023", "HD11300001", "04-2023");
        when(electricBoardService.update(any(ElectricBoardEntity.class)))
                .thenReturn(board);

        ResponseEntity<?> response = electricBoardController.update(board);
        verify(electricBoardService, times(1)).update(board);
        assertEquals(board, response.getBody());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testUpdateWithNullInput(){
        doThrow(new IllegalArgumentException()).when(electricBoardService).update(null);
        electricBoardController.update(null);
    }

    //test with invalid number with non number character
    @Test
    public void testUpdateWithMissingMeterCode_ReturnMessage(){
        ElectricBoardEntity board = new ElectricBoardEntity(null,1530,1686,
                "04-05-2023", "HD11300001", "04-2023");
        when(electricBoardService.update(any(ElectricBoardEntity.class)))
                .thenReturn(board);

        ResponseEntity<?> response = electricBoardController.update(board);
        verify(electricBoardService, times(1)).update(board);
        assertEquals("Vui lòng nhập mã công tơ điện", response.getBody());
    }

    @Test
    public void testUpdateWithMissingNewNumber_ReturnMessage(){
        ElectricBoardEntity board = new ElectricBoardEntity();
        board.setMeterCode("PAC001");
        board.setOldNumber(1686);
        board.setTimeReadMeter("04-05-2023");
        board.setUsername("HD11300001");
        board.setPeriod("04-2023");
        when(electricBoardService.update(any(ElectricBoardEntity.class)))
                .thenReturn(board);

        ResponseEntity<?> response = electricBoardController.update(board);
        verify(electricBoardService, times(1)).update(board);
        assertEquals("Vui lòng nhập số công tơ điện tháng này", response.getBody());
    }

    @Test
    public void testUpdateWithMissingTimeRead_ReturnMessage(){
        ElectricBoardEntity board = new ElectricBoardEntity();
        board.setMeterCode("PAC001");
        board.setOldNumber(1586);
        board.setNewNumber(1698);
        board.setUsername("HD11300001");
        board.setPeriod("04-2023");
        when(electricBoardService.update(any(ElectricBoardEntity.class)))
                .thenReturn(board);

        ResponseEntity<?> response = electricBoardController.update(board);
        verify(electricBoardService, times(1)).update(board);
        assertEquals("Vui lòng nhập thời gian lấy số công tơ điện", response.getBody());
    }

    @Test
    public void testUpdateWithMissingCustomerId_ReturnMessage(){
        ElectricBoardEntity board = new ElectricBoardEntity();
        board.setMeterCode("PAC001");
        board.setOldNumber(1586);
        board.setNewNumber(1698);
        board.setTimeReadMeter("04-05-2023");
        board.setPeriod("04-2023");
        when(electricBoardService.update(any(ElectricBoardEntity.class)))
                .thenReturn(board);

        ResponseEntity<?> response = electricBoardController.update(board);
        verify(electricBoardService, times(1)).update(board);
        assertEquals("Vui lòng nhập mã khách hàng", response.getBody());
    }

    @Test
    public void testUpdateWithMissingPeriod_ReturnMessage(){
        ElectricBoardEntity board = new ElectricBoardEntity();
        board.setMeterCode("PAC001");
        board.setOldNumber(1586);
        board.setNewNumber(1698);
        board.setTimeReadMeter("04-05-2023");
        board.setUsername("HD11300001");
        when(electricBoardService.update(any(ElectricBoardEntity.class)))
                .thenReturn(board);
        ResponseEntity<?> response = electricBoardController.update(board);
        verify(electricBoardService, times(1)).update(board);
        assertEquals("Vui lòng nhập kỳ hạn", response.getBody());
    }

    @Test
    public void testUpdateWithInvalidInput_OldNumberBiggerThanNewNumber_ReturnMessage(){
        ElectricBoardEntity board = new ElectricBoardEntity();
        board.setMeterCode("PAC001");
        board.setOldNumber(1686);
        board.setNewNumber(1000);
        board.setTimeReadMeter("04-05-2023");
        board.setUsername("HD11300001");
        board.setPeriod("04-2023");

        when(electricBoardService.update(any(ElectricBoardEntity.class)))
                .thenReturn(board);
        ResponseEntity<?> response = electricBoardController.update(board);
        verify(electricBoardService, times(1)).update(board);
        assertEquals("Số công tơ điện mới phải lớn hơn số công tơ điện cũ", response.getBody());
    }

    @Test
    public void testGetOneByIdSuccess_ReturnElectricBoard() {
        ElectricBoardEntity board = new ElectricBoardEntity();
        board.setId(1);
        board.setMeterCode("PAC001");
        board.setOldNumber(1686);
        board.setNewNumber(1000);
        board.setTimeReadMeter("04-05-2023");
        board.setUsername("HD11300001");
        board.setPeriod("04-2023");
        when(electricBoardService.getOneById(board.getId()))
                .thenReturn(board);

        ResponseEntity<?> response = electricBoardController.getOneById(board.getId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(board, response.getBody());
    }

    @Test
    public void testGetOneByIdWithNotExistId_ReturnElectricBoard() {
        ElectricBoardEntity board = new ElectricBoardEntity();
        board.setId(1);
        board.setMeterCode("PAC001");
        board.setOldNumber(1686);
        board.setNewNumber(1000);
        board.setTimeReadMeter("04-05-2023");
        board.setUsername("HD11300001");
        board.setPeriod("04-2023");
        when(electricBoardService.getOneById(board.getId()))
                .thenReturn(board);

        ResponseEntity<?> response = electricBoardController.getOneById(100);
        assertEquals("Không tìm thấy bảng số điện", response.getBody());
    }

    @Test
    public void testGetAllByUsernameSuccessWithNotNullResult_ReturnListResult() {
        List<ElectricBoardEntity> list = new ArrayList<>();
        list.add(new ElectricBoardEntity("PAC001",1530,1686,
                "04-05-2023", "HD11300001", "04-2023"));

        when(electricBoardService.getAllByCustomerUserName("HD11300001"))
                .thenReturn(list);

        ResponseEntity<?> response = electricBoardController.getAllByUsername("HD11300001");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());
    }

    @Test
    public void testGetAllByUsernameSuccessWithNullResult_ReturnMessage() {
        List<ElectricBoardEntity> list = new ArrayList<>();
        list.add(new ElectricBoardEntity("PAC001",1530,1686,
                "04-05-2023", "HD11300001", "04-2023"));

        when(electricBoardService.getAllByCustomerUserName("HD11300002"))
                .thenReturn(null);

        ResponseEntity<?> response = electricBoardController.getAllByUsername("HD11300002");
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Khách hàng không có hóa đơn nào", response.getBody());
    }

    @Test
    public void testGetAllByUsernameWithNotExistUsername_ReturnMessage() {
        List<ElectricBoardEntity> list = new ArrayList<>();

        when(electricBoardService.getAllByCustomerUserName("HD11310000"))
                .thenReturn(null);
        ResponseEntity<?> response = electricBoardController.getAllByUsername("HD11310000");
        assertEquals("Không có khách hàng cần tìm", response.getBody());
    }
}