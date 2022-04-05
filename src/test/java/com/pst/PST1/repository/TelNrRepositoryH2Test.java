package com.pst.PST1.repository;

import com.pst.PST1.model.TelNr;
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
public class TelNrRepositoryH2Test {
    @Autowired
    private TelNrRepositoryH2 repository;

    @Test
    public void save_Test(){
        TelNr nr = new TelNr(1L, "+37060111110", 2L);
        repository.save(nr);

        TelNr nrByPhone = repository.findByTelNr("+37060111110");

        assertNotNull(nrByPhone);
        assertEquals(2L, nrByPhone.getUserId());
    }

    @Test
    public void findAll_Test(){
        TelNr nr = new TelNr(1L, "+37060111110", 2L);
        repository.save(nr);

        Iterable<TelNr> telNr = repository.findAll();

        assertNotNull(telNr);

        List<TelNr> result = new ArrayList<TelNr>();
        telNr.forEach(result::add);

        assertEquals(1, result.size());
    }

    @Test
    public void delete_Test(){
        TelNr nr = new TelNr(1L, "+37060111110", 2L);
        repository.save(nr);

        TelNr nrById = repository.findByTelNr("+37060111110");
        assertNotNull(nrById);

        repository.delete(nrById);

        Iterable<TelNr> telNr = repository.findAll();
        List<TelNr> result = new ArrayList<>();
        telNr.forEach(result::add);

        assertEquals(0, result.size());

    }

    @Test
    public void findByNumber_Test(){
        TelNr nr1 = new TelNr(1L, "+37060111110", 1L);
        TelNr nr2 = new TelNr(2L, "+37060111111", 2L);
        TelNr nr3 = new TelNr(3L, "+37060111112", 2L);

        repository.save(nr1);
        repository.save(nr2);
        repository.save(nr3);

        TelNr telNr = repository.findByTelNr("+37060111111");

        assertEquals(telNr.getTelNr(),"+37060111111" );
    }

    @Test
    public void findByUser_Test(){
        TelNr nr1 = new TelNr(1L, "+37060111110", 1L);
        TelNr nr2 = new TelNr(2L, "+37060111111", 2L);
        TelNr nr3 = new TelNr(3L, "+37060111112", 2L);

        repository.save(nr1);
        repository.save(nr2);
        repository.save(nr3);

        Iterable<TelNr> telNr = repository.findByUserId(2L);
        List<TelNr> result = StreamSupport.stream(telNr.spliterator(), false).collect(Collectors.toList());

        assertEquals(2, result.size());
    }


}
