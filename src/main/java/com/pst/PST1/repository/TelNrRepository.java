package com.pst.PST1.repository;

import com.pst.PST1.model.TelNr;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class TelNrRepository {
//    private static List<TelNr> numeriai = new ArrayList<>();
//
//    static {
//        numeriai.add(new TelNr(1L, "+37061111111", 1L));
//        numeriai.add(new TelNr(2L, "+37061111112", 2L));
//        numeriai.add(new TelNr(3L, "+37061111113", 3L));
//        numeriai.add(new TelNr(4L, "+37061111114", 4L));
//    }
//
//    public List<TelNr> findAll(){
//        return numeriai;
//    }
//
//
//    public TelNr findNumberById(Long id){
//        Iterator<TelNr> iterator = numeriai.iterator();
//
//        while (iterator.hasNext()){
//            TelNr numeris = iterator.next();
//            if (numeris.getId() == id){
//                return numeris;
//            }
//        }
//        return null;
//    }
//
//    public String findNumberByIdToString(Long id){
//        Iterator<TelNr> iterator = numeriai.iterator();
//
//        while (iterator.hasNext()){
//            TelNr numeris = iterator.next();
//            if (numeris.getId() == id){
//                return numeris.getTelNr();
//            }
//        }
//        return "";
//    }
//
//
//    public TelNr findByNumber(String number){
//        Iterator<TelNr> iterator = numeriai.iterator();
//
//        while (iterator.hasNext()){
//            TelNr numeris = iterator.next();
//            if (numeris.getTelNr() == number){
//                return numeris;
//            }
//        }
//        return null;
//    }
//
//
//    public List<TelNr> findNumbersByUserId(Long userId){
//        List<TelNr> result = new ArrayList<>();
//        for(TelNr item: numeriai){
//            if (item.getUserId().equals(userId)) result.add(item);
//        }
//        return result;
//    }
//
//    public void updateNumbers(TelNr telNr){
//        if (telNr == null) return;
//
//        for (int i=0; i< numeriai.size(); i++){
//            if (numeriai.get(i).getId() == telNr.getId()){
//                numeriai.set(i, telNr);
//            }
//        }
//    }
//
//    public TelNr addNumber(TelNr telNr){
//        numeriai.add(telNr);
//        return telNr;
//    }
//
//    public void deleteNumberById(Long id){
//        Iterator<TelNr> iterator = numeriai.iterator();
//        while (iterator.hasNext()){
//            TelNr numeris = iterator.next();
//            if (numeris.getId() == id){
//                iterator.remove();
//            }
//        }
//    }
//
//    public void delete(TelNr telNr){
//        deleteNumberById(telNr.getId());
//    }
}
