package com.pst.PST1.controller;

import com.pst.PST1.Pst1Application;
import com.pst.PST1.model.Saskaita;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(classes = Pst1Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SaskaitosRestControllerIT {
    @LocalServerPort
    private int port;

    @Test
    void showSaskaitaById_Test() throws Exception{
        String url = "http://localhost:" + port	+ "/saskaitos/1";

        // Skirtas request siuntimui ir response gavimui
        TestRestTemplate restTemplate = new TestRestTemplate();

        // restTemplate siunčia get request adresu url
        // atsakymas suformuojamas kaip string
        String   responseAsString = restTemplate.getForObject(url, String.class);

        String expected = "{\"id\":1,\"telNrId\":1,\"menuo\":1,\"suma\":30.99,\"telNr\":null}";

        JSONAssert.assertEquals(expected, responseAsString, false);
    }

    @Test
    void addSaskaita_Test(){
        String url = "http://localhost:" + port	+ "/saskaitos";

        // Skirtas request siuntimui ir response gavimui
        TestRestTemplate restTemplate = new TestRestTemplate();

        // Nustatome headerius
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Saskaita saskaita = new Saskaita(5L, 1L, 4, 7.99F);

        // Kuriamas request su duotu body - nr ir duotais header - headers
        // requeste siunčiamas visas Saskaita objektas
        HttpEntity<Saskaita> entity = new HttpEntity<Saskaita>(saskaita, headers);

        // siunčiamas request gaunamas response
        // adresu - url, tipo - POST, su nustatytais body ir headeriais - entity
        // atsakymas laukiamas String pavidalu (json)
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);

        // Tikrinama, ar statusas created (201)
        assertEquals(201, response.getStatusCodeValue());

        // Iš header pasiimamas naujai sukurto resurso URI adresas
        String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);

        // tikrinama, ar gautas header location yra toks, kokio buvo tiketasi
        assertTrue(actual.contains("/saskaitos/5"));
    }
}
