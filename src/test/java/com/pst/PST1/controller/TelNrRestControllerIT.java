package com.pst.PST1.controller;

import com.pst.PST1.Pst1Application;
import com.pst.PST1.model.TelNr;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = Pst1Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TelNrRestControllerIT {

    @LocalServerPort
    private int port;


    @Test
    void showNumberById_Test() throws Exception{
        String url = "http://localhost:" + port	+ "/numeriai/1";

        // Skirtas request siuntimui ir response gavimui
        TestRestTemplate restTemplate = new TestRestTemplate();

        // restTemplate siunčia get request adresu url
        // atsakymas suformuojamas kaip string
        String responseAsString = restTemplate.getForObject(url, String.class);

        String expected = "{\"id\":1,\"telNr\":\"+37061111111\",\"userId\":1}";

        JSONAssert.assertEquals(expected, responseAsString, false);
    }

    @Test
    void addNumber_Test(){
        String url = "http://localhost:" + port	+ "/numeriai";

        // Skirtas request siuntimui ir response gavimui
        TestRestTemplate restTemplate = new TestRestTemplate();

        // Nustatome headerius
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        TelNr nr = new TelNr(1L, "+37061111111", 1L);

        // Kuriamas request su duotu body - nr ir duotais header - headers
        // requeste siunčiamas visas TelNr objektas
        HttpEntity<TelNr> entity = new HttpEntity<TelNr>(nr, headers);

        // siunčiamas request gaunamas response
        // adresu - url, tipo - POST, su nustatytais body ir headeriais - entity
        // atsakymas laukiamas String pavidalu (json)
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        // Tikrinama, ar statusas created (201)
        assertEquals(201, response.getStatusCodeValue());

        // Iš header pasiimamas naujai sukurto resurso URI adresas
        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

        // tikrinama, ar gautas header location yra toks, kokio buvo tiketasi
        assertTrue(actual.contains("/numeriai/1"));
    }

}
