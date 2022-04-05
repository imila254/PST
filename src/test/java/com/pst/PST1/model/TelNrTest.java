package com.pst.PST1.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class TelNrTest {

    @Test
    void TelNr_LongStringLong_Test(){
        TelNr nr = new TelNr(1L, "+37061111111",1L);
        assertAll("Test TelNr Constructor",
                () -> assertEquals(1, nr.getId()),
                () -> assertEquals("+37061111111", nr.getTelNr()),
                () -> assertEquals(1, nr.getUserId())
        );
    }

    @Test
    void compareTo_Test(){
        TelNr nr1 = new TelNr(1L, "+37061111111", 1L);
        TelNr nr2 = new TelNr(1L, "+37061111111", 1L);
        assertEquals(0, nr1.compareTo(nr2));
    }

    @Test
    void EqualsObject_Test(){
        TelNr nr1 = new TelNr(1L, "+37061111111", 1L);
        TelNr nr2 = new TelNr(1L, "+37061111111", 1L);
        assertTrue(nr1.equals(nr2));
    }

    @Test
    void setId_Test(){
        TelNr nr = new TelNr();
        assertThrows(NullPointerException.class, () -> { nr.setId(-1L); });
    }

    @Test
    void setTelNr_Test(){
        TelNr nr = new TelNr();
        assertThrows(NullPointerException.class, () -> { nr.setTelNr(null); });
    }

    @Test
    void setUserId_Test(){
        TelNr nr = new TelNr();
        assertThrows(NullPointerException.class, () -> { nr.setUserId(-1L);});
        assertThrows(IllegalArgumentException.class, () -> { nr.setTelNr("+3706111111");});
    }

    @ParameterizedTest
    @ValueSource(strings = { "abc", "+3701111111111", "861111111111", "+370a1111111" })
    void setUserId_InvalidNumber_Test(String string){
        TelNr nr = new TelNr();
        assertThrows(IllegalArgumentException.class, () -> { nr.setTelNr(string);});
    }

}
