package com.pst.PST1.repository;

import com.pst.PST1.model.Saskaita;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class SaskaitaRepositoryH2Test {
    @Autowired
    private SaskaitaRepositoryH2 repository;

    @Test
    public void save_Test(){
        Saskaita s1 = new Saskaita(1L, 1L, 4, 9.99F);
        Saskaita s2 = new Saskaita(2L, 2L, 4, 9.99F);
        Saskaita s3 = new Saskaita(3L, 1L, 4, 9.99F);

        repository.save(s1);
        repository.save(s2);
        repository.save(s3);

        List<Saskaita> saskaitaByTelNrId = repository.findByTelNrId(1L);

        assertNotNull(saskaitaByTelNrId);
        assertEquals(2L, saskaitaByTelNrId.size());
    }

    @Test
    public void findAll_Test(){
        Saskaita s1 = new Saskaita(1L, 1L, 4, 9.99F);
        repository.save(s1);

        Iterable<Saskaita> saskaita = repository.findAll();
        assertNotNull(saskaita);

        List<Saskaita> result = new ArrayList<>();
        saskaita.forEach(result::add);

        assertEquals(1, result.size());
    }

    @Test
    public void delete_Test(){
        Saskaita s1 = new Saskaita(1L, 1L, 4, 9.99F);
        Saskaita s2 = new Saskaita(2L, 1L, 4, 9.99F);
        repository.save(s1);
        repository.save(s2);

        repository.deleteById(1L);

        Iterable<Saskaita> saskaita = repository.findAll();
        List<Saskaita> res = new ArrayList<>();
        saskaita.forEach(res::add);

        assertEquals(1, res.size());

    }

    @Test
    public void findByTelNrId_Test(){
        Saskaita s1 = new Saskaita(1L, 1L, 4, 9.99F);
        Saskaita s2 = new Saskaita(2L, 1L, 4, 9.99F);
        Saskaita s3 = new Saskaita(3L, 2L, 4, 9.99F);

        repository.save(s1);
        repository.save(s2);
        repository.save(s3);

        Iterable<Saskaita> s = repository.findByTelNrId(1L);
        List<Saskaita> result = StreamSupport.stream(s.spliterator(), false).collect(Collectors.toList());

        assertEquals(2, result.size());
    }

    @Test
    public void findByMonth_Test(){
        Saskaita s1 = new Saskaita(1L, 1L, 4, 9.99F);
        Saskaita s2 = new Saskaita(2L, 1L, 3, 9.99F);
        Saskaita s3 = new Saskaita(3L, 2L, 4, 9.99F);

        repository.save(s1);
        repository.save(s2);
        repository.save(s3);

        Iterable<Saskaita> s = repository.findByMenuo(4);
        List<Saskaita> result = StreamSupport.stream(s.spliterator(), false).collect(Collectors.toList());

        assertEquals(2, result.size());

    }
}
