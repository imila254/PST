package com.pst.PST1.controller;

import com.pst.PST1.model.Saskaita;
import com.pst.PST1.service.SaskaitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SaskaitosController {
    @Autowired
    SaskaitaService service;

    public SaskaitosController(){super();}

    public SaskaitosController(SaskaitaService service){
        super();
        this.service = service;
    }


    // http://localhost:8080/list-saskaitos
    @GetMapping("/list-saskaitos")
    public String showAll(ModelMap modelMap){
        modelMap.put("saskaitos", service.addNumerToSaskaita());
        return "list-saskaitos"; // list-saskaitos.jsp
    }

    // http://localhost:8080/add-saskaita
    @GetMapping("/add-saskaita")
    public String showAddPage(ModelMap modelMap){
        modelMap.addAttribute("saskaita", new Saskaita(service.findMaxId() + 1L, 1L, 1, 0.99F));
        return "saskaita";
    }

    // http://localhost:8080/add-saskaita
    @PostMapping("/add-saskaita")
    public String add(ModelMap modelMap, @ModelAttribute("saskaita") Saskaita saskaita, BindingResult result){
        if(result.hasErrors()) return "saskaita";
        service.add(saskaita);
        return "redirect:/list-saskaitos";
    }

    // http://localhost:8080/update-saskaita/1
    @GetMapping("/update-saskaita/{saskaitaId}")
    public String showUpdatePage(ModelMap modelMap, @PathVariable Long saskaitaId){
        modelMap.addAttribute("saskaita", service.findById(saskaitaId));
        return "saskaita";
    }

    // http://localhost:8080/update-saskaita/1
    @PostMapping("/update-saskaita/{saskaitaId}")
    public String update(ModelMap modelMap, @ModelAttribute("saskaita") Saskaita saskaita, BindingResult result){
        if(result.hasErrors()) return "saskaita";
        service.update(saskaita);
        return "redirect:/list-saskaitos";
    }

    // http://localhost:8080/delete-saskaita/1
    @GetMapping("/delete-saskaita/{saskaitaId}")
    public String delete(@PathVariable Long saskaitaId){
        service.deleteById(saskaitaId);
        return "redirect:/list-saskaitos";
    }

}
