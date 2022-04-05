package com.pst.PST1.repository;

import com.pst.PST1.model.Saskaita;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class SaskaitaRepository {
    private static List<Saskaita> saskaita = new ArrayList<>();

    static {
        saskaita.add(new Saskaita(1L, 1L, 4, 30.99F));
        saskaita.add(new Saskaita(2L, 1L, 4, 3.99F));
        saskaita.add(new Saskaita(3L, 2L, 8, 9.99F));
    }

    public List<Saskaita> findAll(){
        return saskaita;
    }


    public Saskaita findById(Long id){
        Iterator<Saskaita> iterator = saskaita.iterator();
        while (iterator.hasNext()){
            Saskaita s = iterator.next();
            if (s.getId() == id) return s;
        }
        return null;
    }

    public List<Saskaita> findByTelNrId(Long id){
        List<Saskaita> res = new ArrayList<>();
        for (Saskaita item: saskaita){
            if (item.getTelNrId().equals(id)) res.add(item);
        }
        return res;
    }

    public List<Saskaita> findByMonth(int menuo){
        List<Saskaita> res = new ArrayList<>();
        for (Saskaita item: saskaita){
            if (item.getMenuo() == menuo) res.add(item);
        }
        return res;
    }

    public void update(Saskaita s){
        if (s == null) return;

        for(int i = 0;i < saskaita.size(); i++){
            if(saskaita.get(i).getId() == s.getId()){
                saskaita.set(i,s);
            }
        }
    }

    public Saskaita add(Saskaita s){
        saskaita.add(s);
        return s;
    }

    public void deleteById(Long id){
        Iterator<Saskaita> iterator = saskaita.iterator();
        while(iterator.hasNext()){
            Saskaita s = iterator.next();
            if(s.getId() == id){
                iterator.remove();
            }
        }
    }

    public void delete(Saskaita s){
        deleteById(s.getId());
    }


}
