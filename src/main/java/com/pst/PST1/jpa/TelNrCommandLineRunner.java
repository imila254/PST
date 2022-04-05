package com.pst.PST1.jpa;

import com.pst.PST1.model.TelNr;
import com.pst.PST1.repository.TelNrRepositoryH2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TelNrCommandLineRunner implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(TelNrCommandLineRunner.class);

    @Autowired
    private TelNrRepositoryH2 repository;

    @Override
    public void run(String... args) throws Exception {

        repository.deleteAll();
        repository.save(new TelNr(1L,"+37061111111", 1L));
        repository.save(new TelNr(2L,"+37061111112", 2L));
        repository.save(new TelNr(3L,"+37061111113", 1L));
        repository.save(new TelNr(4L,"+37061111114", 3L));
        repository.save(new TelNr(5L,"+37061111115", 3L));
        repository.save(new TelNr(6L,"+37061111116", 3L));
        repository.save(new TelNr(7L,"+37061111117", 4L));
        repository.save(new TelNr(8L,"+37061111118", 1L));
        repository.save(new TelNr(9L,"+37061111119", 2L));
        repository.save(new TelNr(10L,"+37061111110", 4L));

    }
}
