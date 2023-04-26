package com.electricitysystem.controller;

import com.electricitysystem.entity.ElectricBoardEntity;
import com.electricitysystem.repository.ElectricBoardRepository;
import com.electricitysystem.service.ElectricBoardService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@WebMvcTest
class ElectricBoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ElectricBoardService electricBoardService;

    @MockBean
    private ElectricBoardRepository electricBoardRepository;
    @Test
    void create() {
    }

    @Test
    void update() {
    }

//    @Test
//    public void getOneById() {
//        Integer id = 1;
//        when(electricBoardService.getOneById(id)).thenReturn(
//                new ElectricBoardEntity(1, "PA0001", 1550, 1259, 291, "HD11300001",663053.6)
//        );
//        Assertions.assertEquals("HD11300001", electricBoardService.getOneById(id).getUsername());
//    }

//    @Test
//    void getAllByUsername() throws Exception {
//        when(electricBoardService.getAllByCustomerUserName(
//                Mockito.anyString())).thenReturn(Arrays.asList(new ElectricBoardEntity(1, "PA0001", 1550, 1259, 291, "HD11300001",663053.6)));
//        mockMvc.perform(get("/board/getAllByUsername?{username=}", "HD11300001")
//                .accept(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk());
//
//        verify(electricBoardService, times(1)).getAllByCustomerUserName(Mockito.anyString());
//    }
}
