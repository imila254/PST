package com.pst.PST1.controller;

import com.pst.PST1.model.Saskaita;
import com.pst.PST1.service.SaskaitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class SaskaitosRestController {
    @Autowired
    SaskaitaService service;

    // http://localhost:8080/saskaitos
    @GetMapping("/saskaitos")
    public List<Saskaita> saskaitosJson(){
        return service.findAll();
    }


    // http://localhost:8080/saskaitos/2
    @GetMapping("/saskaitos/{saskaitaId}")
    public Optional<Saskaita> saskaitaById(@PathVariable Long saskaitaId){
        return service.findById(saskaitaId);
    }


    // http://localhost:8080/saskaitos
    //{"id": 9, "telNrId": 2, "menuo": 4, "suma": 99.99,"telNr": null}
   @PostMapping("/saskaitos")
    public ResponseEntity<Void> addSaskaita(@RequestBody Saskaita newsaskaita){
        Saskaita s = service.add(newsaskaita);

        if(s == null) return ResponseEntity.noContent().build();

       URI location = ServletUriComponentsBuilder.fromCurrentRequest()
               .path("/{id}").buildAndExpand(s.getId()).toUri();

       return ResponseEntity.created(location).build();
   }

    @DeleteMapping("/saskaitos/{saskaitaId}")
    void delete(@PathVariable Long saskaitaId) {
        service.deleteById(saskaitaId);
    }


}
