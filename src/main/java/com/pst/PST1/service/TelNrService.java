package com.pst.PST1.service;

import com.pst.PST1.model.Saskaita;
import com.pst.PST1.model.TelNr;
import com.pst.PST1.repository.TelNrRepositoryH2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class TelNrService {

    @Autowired
    private TelNrRepositoryH2 repositoryH2;

    @Autowired
    private SaskaitaService saskaitaService;

    public void deleteInBothTablesById(Long id){

        List<Saskaita> saskaita = saskaitaService.findAll();

        Iterator<Saskaita> iterator = saskaita.stream().iterator();
        while (iterator.hasNext()){
            Saskaita s = iterator.next();
            if (s.getTelNrId() == id) saskaitaService.delete(s);
        }

        repositoryH2.deleteById(id);

    }

    public List<TelNr> findAll(){return (List<TelNr> ) repositoryH2.findAll();}

    public Optional<TelNr> findNumberById(Long id){ return repositoryH2.findById(id);}

    public String findNumberByIdToString(Long id){
        Optional<TelNr> telNr = repositoryH2.findById(id);
        Iterator<TelNr> iterator = telNr.stream().iterator();

        while (iterator.hasNext()){
            TelNr numeris = iterator.next();
            if (numeris.getId() == id){
                return numeris.getTelNr();
            }
        }
        return "";

    }

    public TelNr findByNumber(String number){return repositoryH2.findByTelNr(number);}

    public Long findMaxId(){
        Iterable<TelNr> telNr = repositoryH2.findAll();
        Long max = 0L;
        for (TelNr item : telNr){
            if(item.getId() > max) max = item.getId();
        }
        return max;
    }

    public List<TelNr>findNumbersByUserId(Long userId){
          return repositoryH2.findByUserId(userId);
    }

    public void update(TelNr telNr){
        repositoryH2.save(telNr);
    }

    public TelNr add(TelNr telNr){
        return repositoryH2.save(telNr);
    }

    public void deleteById(Long id){
        repositoryH2.deleteById(id);
    }

    public void delete(TelNr telNr){
        repositoryH2.delete(telNr);
    }

    public void save (TelNr nr){
        repositoryH2.save(nr);
    }
}
