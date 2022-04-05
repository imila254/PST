package com.pst.PST1.service;

import com.pst.PST1.model.TelNr;
import com.pst.PST1.repository.TelNrRepository;
import com.pst.PST1.repository.TelNrRepositoryH2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TelNrServiceTest {

    @Mock
    TelNrRepositoryH2 repository;

    @InjectMocks
    TelNrService service;

    @Test
    void findAll_Test(){
        TelNr telNr = new TelNr();
        List<TelNr> numeriai = new ArrayList<>();
        numeriai.add(telNr);

        when(repository.findAll()).thenReturn(numeriai);

        List<TelNr> found = service.findAll();

        // tikrina, ar repository metodas findAll() buvo iškviestas vieną kartą
        verify(repository).findAll();

        // patikrinamas service metodas findAll()
        assertEquals(1, found.size());
    }


    @Test
    void findByNumber_Test(){
        TelNr telNr = new TelNr();
        when(repository.findByTelNr(Mockito.anyString())).thenReturn(telNr);

        TelNr found = service.findByNumber(Mockito.anyString());
        verify(repository).findByTelNr(Mockito.anyString());

        assertNotNull(found);
    }


    @Test
    void findNumberByUserId_Test(){
        TelNr telNr = new TelNr();
        List<TelNr> numeriai = new ArrayList<>();

        numeriai.add(telNr);

        when(repository.findByUserId(Mockito.anyLong())).thenReturn(numeriai);

        List<TelNr> found = service.findNumbersByUserId(Mockito.anyLong());
        verify(repository).findByUserId(Mockito.anyLong());

        assertNotNull(found);
    }


    @Test
    void update_Test(){
        TelNr telNr = new TelNr();
        service.update(telNr);
        verify(repository).save(Mockito.any(TelNr.class));
    }

    @Test
    void add_Test(){
        TelNr telNr = new TelNr();
        when(repository.save(Mockito.any(TelNr.class))).thenReturn(telNr);

        TelNr added = service.add(telNr);
        verify(repository).save(Mockito.any(TelNr.class));
        assertNotNull(added);
    }

    @Test
    void deleteByIdTest(){
        service.deleteById(1L);
        verify(repository).deleteById(Mockito.anyLong());
    }

    @Test
    void delete_Test(){
        TelNr telNr = new TelNr();
        service.delete(telNr);
        verify(repository).delete(Mockito.any(TelNr.class));
    }


}
