package com.pst.PST1.model;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SaskaitaTest {

    @Test
    void Saskaita_LongLongIntFloat_Test(){
        Saskaita saskaita = new Saskaita(1L, 1L, 4, 10.99F);
        assertAll("Test Saskaita Constructor",
                () -> assertEquals(1L, saskaita.getId()),
                () -> assertEquals(1L, saskaita.getTelNrId()),
                () -> assertEquals(4, saskaita.getMenuo()),
                () -> assertEquals(10.99F, saskaita.getSuma())
        );
    }

    @Test
    void compareTo_Test(){
        Saskaita saskaita1 = new Saskaita(1L, 1L, 4, 10.99F);
        Saskaita saskaita2 = new Saskaita(1L, 1L, 4, 10.99F);
        assertEquals(0, saskaita1.compareTo(saskaita2));
    }


    @Test
    void EqualsObject_Test(){
        Saskaita saskaita1 = new Saskaita(1L, 1L, 4, 10.99F);
        Saskaita saskaita2 = new Saskaita(1L, 1L, 4, 10.99F);
        assertTrue(saskaita1.equals(saskaita2));
    }

    @Test
    void setId_Test(){
        Saskaita saskaita = new Saskaita();
        assertThrows(NullPointerException.class, () -> { saskaita.setId(-1L); });
    }

    @Test
    void setTelNrId_Test(){
        Saskaita saskaita = new Saskaita();
        assertThrows(NullPointerException.class, () -> { saskaita.setTelNrId(-1L); });
    }

    @Test
    void setMenuo_Test(){
        Saskaita saskaita = new Saskaita();
        assertThrows(IllegalArgumentException.class, () -> { saskaita.setMenuo(-1); });
    }

    @Test
    void setSuma_Test(){
        Saskaita saskaita = new Saskaita();
        assertThrows(IllegalArgumentException.class, () -> { saskaita.setSuma(-1); });
    }

}
