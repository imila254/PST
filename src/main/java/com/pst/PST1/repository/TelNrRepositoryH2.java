package com.pst.PST1.repository;

import com.pst.PST1.model.TelNr;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TelNrRepositoryH2 extends CrudRepository<TelNr, Long> {

    TelNr findByTelNr(String number);

    List<TelNr> findByUserId(Long userId);

}

