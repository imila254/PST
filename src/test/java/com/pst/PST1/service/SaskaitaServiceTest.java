package com.pst.PST1.service;

import com.pst.PST1.model.Saskaita;
import com.pst.PST1.repository.SaskaitaRepositoryH2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SaskaitaServiceTest {
    @Mock
    SaskaitaRepositoryH2 repository;

    @InjectMocks
    SaskaitaService service;

    @Test
    void findAll_Test(){
        Saskaita s = new Saskaita();
        List<Saskaita> saskaitos = new ArrayList<>();
        saskaitos.add(s);

        when(repository.findAll()).thenReturn(saskaitos);
        List<Saskaita> found = service.findAll();

        verify(repository).findAll();
        assertEquals(1, found.size());
    }

    @Test
    void FindById_Test(){
        Saskaita s = new Saskaita();
        when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(s));

        Optional<Saskaita> found = service.findById(1L);
        verify(repository).findById(Mockito.anyLong());
        assertNotNull(found);
    }

    @Test
    void findByTelNrId_Test(){
        Saskaita s = new Saskaita();
        List<Saskaita> saskaitos = new ArrayList<>();

        saskaitos.add(s);
        when(repository.findByTelNrId(Mockito.anyLong())).thenReturn(saskaitos);

        List<Saskaita> found = service.findByTelNrId(Mockito.anyLong());
        verify(repository).findByTelNrId(Mockito.anyLong());
        assertNotNull(found);

    }

    @Test
    void findByMonth_Test(){
        Saskaita s = new Saskaita();
        List<Saskaita> saskaitos = new ArrayList<>();

        saskaitos.add(s);
        when(repository.findByMenuo(Mockito.anyInt())).thenReturn(saskaitos);

        List<Saskaita> found = service.findByMonth(Mockito.anyInt());
        verify(repository).findByMenuo(Mockito.anyInt());
        assertNotNull(found);

    }

    @Test
    void update_Test(){
        Saskaita s = new Saskaita();
        service.update(s);
        verify(repository).save(Mockito.any(Saskaita.class));
    }

    @Test
    void add_Test(){
        Saskaita s = new Saskaita();
        when(repository.save(Mockito.any(Saskaita.class))).thenReturn(s);

        Saskaita added = service.add(s);
        verify(repository).save(Mockito.any(Saskaita.class));
        assertNotNull(added);
    }

    @Test
    void deleteById_Test(){
        service.deleteById(1L);
        verify(repository).deleteById(Mockito.anyLong());
    }

    @Test
    void delete_Test(){
        Saskaita s = new Saskaita();
        service.delete(s);

        verify(repository).delete(Mockito.any(Saskaita.class));
    }

}
