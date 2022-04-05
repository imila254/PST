package com.pst.PST1.service;

import com.pst.PST1.model.Saskaita;
import com.pst.PST1.repository.SaskaitaRepositoryH2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SaskaitaService {

    @Autowired
    private SaskaitaRepositoryH2 repositoryH2;

    @Autowired
    TelNrService telNrService;


    public List<Saskaita> findAll(){return (List<Saskaita>)repositoryH2.findAll();}

    public Optional<Saskaita> findById(Long id){return repositoryH2.findById(id);}

    public List<Saskaita> addNumerToSaskaita(){
        List<Saskaita> saskaita = (List<Saskaita>) repositoryH2.findAll();
        for (Saskaita value : saskaita) {
            System.out.println(telNrService.findNumberByIdToString(value.getTelNrId()));
            value.setTelNr(telNrService.findNumberByIdToString(value.getTelNrId()));
        }

        return saskaita;
    }

    public Long findMaxId(){
        List<Saskaita> saskaita = (List<Saskaita>)repositoryH2.findAll();
        Long max = 0L;
        for (Saskaita item : saskaita){
            if(item.getId() > max) max = item.getId();
        }
        return max;
    }

    public List<Saskaita> findByTelNrId(Long id){
    return repositoryH2.findByTelNrId(id);
}

    public List<Saskaita> findByMonth(int menuo){return repositoryH2.findByMenuo(menuo);}

    public void update(Saskaita s){
        repositoryH2.save(s);
    }

    public Saskaita add(Saskaita s){
    return repositoryH2.save(s);
}

    public void deleteById(Long id){
        repositoryH2.deleteById(id);
    }

    public void delete(Saskaita s){
        repositoryH2.delete(s);
    }

    public void save (Saskaita s){
        repositoryH2.save(s);
    }
}
