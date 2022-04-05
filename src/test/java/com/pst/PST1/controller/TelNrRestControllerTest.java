package com.pst.PST1.controller;

import com.pst.PST1.model.TelNr;
import com.pst.PST1.service.TelNrService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WebMvcTest(value = TelNrRestController.class)
public class TelNrRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    TelNrService service;

    @Test
    void ShowNumberList_Test() throws Exception{
        List<TelNr> telNr = new ArrayList<>();
        telNr.add(new TelNr(1L, "861111111", 4L));
        telNr.add(new TelNr(2L, "861111112", 1L));

        when(service.findAll()).thenReturn(telNr);

        //siunčiamas GET requestas su tuščiu body ir reikalingais headeriais:
        // adresu /numeriai
        // tikimasi JSON pavidalo response
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/numeriai")
                .accept(MediaType.APPLICATION_JSON);

        // siunčia RequestBuilderio pagamintą request'ą
        // tikisi, kad response statusas yra 200 (OK)
        // pasiima respons'ą JSON pavidalu
        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String expected = "[{\"id\":1,\"telNr\":\"861111111\",\"userId\":4},{\"id\":2,\"telNr\":\"861111112\"}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void NumberById_Test() throws Exception {
        TelNr nr = new TelNr(1L, "861111111", 4L);
        when(service.findNumberById(Mockito.anyLong())).thenReturn(java.util.Optional.of(nr));

        //siunčiamas GET requestas su tuščiu body ir reikalingai headeriais:
        // adresu /numeriai/1
        // tikimasi JSON pavidalo response
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/numeriai/1")
                .accept(MediaType.APPLICATION_JSON);

        // siunčia RequestBuilderio pagamintą request'ą
        // tikisi, kad response statusas yra 200 (OK)
        // pasiima respons'ą JSON pavidalu
        MvcResult result = mockMvc.perform(rb)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String expected = "{\"id\":1,\"telNr\":\"861111111\",\"userId\":4}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);


    }

    @Test
    void AddNumeris_Test() throws Exception{
        TelNr nr = new TelNr(1L, "861111111", 4L);
        when(service.add(Mockito.any(TelNr.class))).thenReturn(nr);

        String nrJson =  "{\"id\":1,\"telNr\":\"861111111\",\"userId\":4}";

        // siunčiamas POST tipo requestas adresu /numeriai
        // response headeryje atsiųs informaciją apie resurso sukūrimą ir uri adresą su resurso lokacija
        // content - į requestą įdedamas numerių JSON (naujas resursas)
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/numeriai")
                .content(nrJson)
                .contentType(MediaType.APPLICATION_JSON);

        // siunčia RequestBuilderio pagamintą request'ą
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        // pasiimamas response
        MockHttpServletResponse response = result.getResponse();

        // patikriname ar response status is created
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        // tikrinama, ar reasponse Headeryje Location elemento reikšmė yra naujai sukurto resurso URI adresas
        assertEquals("http://localhost/numeriai/1", response.getHeader(HttpHeaders.LOCATION));
    }
}
