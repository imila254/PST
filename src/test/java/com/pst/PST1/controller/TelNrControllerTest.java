package com.pst.PST1.controller;

import com.pst.PST1.model.TelNr;
import com.pst.PST1.service.TelNrService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
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
@WebMvcTest(value = TelNrController.class)
public class TelNrControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TelNrService telNrServiceMock;

    @InjectMocks
    TelNrController telNrController;

    @Test
    void testShowNumberView() throws Exception {
        List<TelNr> nr = new ArrayList<>();
        nr.add(new TelNr(1L, "861111111", 1L));


        when(telNrServiceMock.findAll()).thenReturn(nr);

        //siunčiamas GET requestas su tuščiu body ir reikalingai headeriais:
        // adresu /list-numeriai
        // tikimasi HTML pavidalo response
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/list-numeriai")
                .accept(MediaType.TEXT_HTML);

        // siunčia RequestBuilderio pagamintą request'ą
        // tikisi, kad response statusas yra 200 (OK)
        // yra sukurtas view "list-numeriai"
        // model attribute - numeriai
        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("list-numeriai"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("numeriai"))
                .andReturn();
    }

    @Test
    public void showAddPage_Test() throws Exception {
        RequestBuilder rb = MockMvcRequestBuilders.get("/add-numeris");

        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("numeris"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("numeris"))
                .andReturn();
    }


}
