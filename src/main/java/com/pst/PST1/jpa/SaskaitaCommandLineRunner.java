package com.pst.PST1.jpa;

import com.pst.PST1.model.Saskaita;
import com.pst.PST1.repository.SaskaitaRepositoryH2;
import com.pst.PST1.repository.TelNrRepositoryH2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SaskaitaCommandLineRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(com.pst.PST1.jpa.TelNrCommandLineRunner.class);

    @Autowired
    private SaskaitaRepositoryH2 repository;

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();
        repository.save(new Saskaita(1L, 1L, 1, 30.99F));
        repository.save(new Saskaita(2L, 2L, 4, 3.99F));
        repository.save(new Saskaita(3L, 3L, 8, 9.99F));
        repository.save(new Saskaita(4L, 4L, 4, 5F));
        repository.save(new Saskaita(5L, 5L, 4, 30.99F));
        repository.save(new Saskaita(6L, 1L, 4, 3.99F));
        repository.save(new Saskaita(7L, 2L, 8, 9.99F));
        repository.save(new Saskaita(8L, 3L, 4, 5F));

    }
}
