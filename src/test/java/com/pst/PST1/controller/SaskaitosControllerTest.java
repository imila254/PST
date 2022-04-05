package com.pst.PST1.controller;

import com.pst.PST1.model.Saskaita;
import com.pst.PST1.model.TelNr;
import com.pst.PST1.service.SaskaitaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = SaskaitosController.class)
public class SaskaitosControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    SaskaitaService saskaitaService;

    @InjectMocks
    SaskaitosController saskaitosController;

    @Test
    void testShowNumberView() throws Exception {
        List<Saskaita> saskaitos = new ArrayList<>();
        saskaitos.add(new Saskaita(1L, 1L, 4, 30.99F));
        saskaitos.add(new Saskaita(2L, 2L, 4, 9.99F));
        when(saskaitaService.findAll()).thenReturn(saskaitos);

        RequestBuilder rb = MockMvcRequestBuilders
                .get("/list-saskaitos")
                .accept(MediaType.TEXT_HTML);

        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("list-saskaitos"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("saskaitos"))
                .andReturn();
    }

    @Test
    public void showAddPage_Test() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.get("/add-saskaita");

        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("saskaita"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("saskaita"))
                .andReturn();
    }

}
