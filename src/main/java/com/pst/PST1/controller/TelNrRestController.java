package com.pst.PST1.controller;

import com.pst.PST1.model.TelNr;
import com.pst.PST1.service.TelNrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class TelNrRestController {
    @Autowired
    TelNrService service;

    // http://localhost:8080/numeriai
    @GetMapping("/numeriai")
    public List<TelNr> numeriaiJson(){
        return service.findAll();
    }


    // http://localhost:8080/numeriai/2
    @GetMapping("/numeriai/{numerisId}")
    public Optional<TelNr> numerisById(@PathVariable Long numerisId){
        return service.findNumberById(numerisId);
    }

    // http://localhost:8080/numeriai
    // {"id":11,"telNr":"+37060000000","userId":1}
    @PostMapping("/numeriai")
    public ResponseEntity<Void> addNumeris(@RequestBody TelNr newNr){
        TelNr telNr = service.add(newNr);

        if (telNr == null) return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(telNr.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/numeriai/{numerisId}")
    void delete(@PathVariable Long numerisId) {
        service.deleteInBothTablesById(numerisId);
    }

}
