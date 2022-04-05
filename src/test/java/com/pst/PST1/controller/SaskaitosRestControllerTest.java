package com.pst.PST1.controller;

import com.pst.PST1.model.Saskaita;
import com.pst.PST1.service.SaskaitaService;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = SaskaitosRestController.class)
public class SaskaitosRestControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    SaskaitaService service;

    @Test
    void ShowSaskaitosList_Test() throws Exception {
        List<Saskaita> saskaitos = new ArrayList<>();
        saskaitos.add(new Saskaita(1L, 1L, 4, 30.99F));
        saskaitos.add(new Saskaita(2L, 2L, 4, 9.99F));

        when(service.findAll()).thenReturn(saskaitos);

        //siunčiamas GET requestas su tuščiu body ir reikalingai headeriais:
        // adresu /saskaitos
        // tikimasi JSON pavidalo response
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/saskaitos")
                .accept(MediaType.APPLICATION_JSON);

        // siunčia RequestBuilderio pagamintą request'ą
        // tikisi, kad response statusas yra 200 (OK)
        // pasiima respons'ą JSON pavidalu
        MvcResult result = mockMvc.perform(rb)
                .andExpect(status().isOk())
                .andReturn();

        String expected = "[{\"id\":1,\"telNrId\":1,\"menuo\":4,\"suma\":30.99,\"telNr\":null}," + "{\"id\":2,\"telNrId\":2,\"menuo\":4,\"suma\":9.99,\"telNr\":null}]";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void SaskaitaById_Test() throws Exception {
        Saskaita saskaita = new Saskaita(1L, 1L, 4, 30.99F);
        when(service.findById(Mockito.anyLong())).thenReturn(java.util.Optional.of(saskaita));

        //siunčiamas GET requestas su tuščiu body ir reikalingai headeriais:
        // adresu /saskaitos/1
        // tikimasi JSON pavidalo response
        RequestBuilder rb = MockMvcRequestBuilders
                .get("/saskaitos/1")
                .accept(MediaType.APPLICATION_JSON);

        // siunčia RequestBuilderio pagamintą request'ą
        // tikisi, kad response statusas yra 200 (OK)
        // pasiima respons'ą JSON pavidalu
        MvcResult result = mockMvc.perform(rb)
                .andExpect(status().isOk())
                .andReturn();

        String expected = "{\"id\":1,\"telNrId\":1,\"menuo\":4,\"suma\":30.99,\"telNr\":null}";

        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void AddSaskaita_Test() throws Exception {
        Saskaita saskaita = new Saskaita(1L, 1L, 4, 30.99F);
        when(service.add(Mockito.any(Saskaita.class))).thenReturn(saskaita);

        String sasakitaJson = "{\"id\":1,\"telNrId\":1,\"menuo\":4,\"suma\":30.99,\"telNr\":null}";

        // siunčiamas POST tipo requestas adresu /saskaitos
        // response headeryje atsiųs informaciją apie resurso sukūrimą ir uri adresą su resurso lokacija
        // content - į requestą įdedamas numerių JSON (naujas resursas)
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/saskaitos")
                .content(sasakitaJson)
                .contentType(MediaType.APPLICATION_JSON);


        // siunčia RequestBuilderio pagamintą request'ą
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        // pasiimamas response
        MockHttpServletResponse response = result.getResponse();

        // patikriname ar response status is created
        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

        // / tikrinama, ar reasponse Headeryje Location elemento reikšmė yra naujai sukurto resurso URI adresas
        assertEquals("http://localhost/saskaitos/1", response.getHeader(HttpHeaders.LOCATION));
    }

}
