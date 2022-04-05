package com.pst.PST1.repository;

import com.pst.PST1.model.Saskaita;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SaskaitaRepositoryH2 extends CrudRepository<Saskaita, Long> {

    List<Saskaita> findByTelNrId(Long telNrId);

    List<Saskaita> findByMenuo(int menuo);
}

