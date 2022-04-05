package com.pst.PST1.controller;

import com.pst.PST1.model.TelNr;
import com.pst.PST1.service.TelNrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class TelNrController {

    @Autowired
    TelNrService service;

    public TelNrController(){
        super();
    }

    public TelNrController(TelNrService service){
        super();
        this.service = service;
    }

    // http://localhost:8080/list-numeriai
    @GetMapping("/list-numeriai")
    public String showAll(ModelMap modelMap){
        modelMap.put("numeriai", service.findAll());
        return "list-numeriai"; // list-numeriai.jsp
    }

    // http://localhost:8080/add-numeris
    @RequestMapping(value = "/add-numeris", method = RequestMethod.GET)
//    @GetMapping("/add-numeris")
    public String showAddNumeris(ModelMap modelMap){
        modelMap.addAttribute("numeris", new TelNr(service.findMaxId() + 1L, "+37060000000", 2L));
        return "numeris";
    }

    // http://localhost:8080/add-numeris
    @RequestMapping(value = "/add-numeris", method = RequestMethod.POST)
    public String addNumeris(ModelMap modelMap, @ModelAttribute("numeris") TelNr telNr, BindingResult result){
        if (result.hasErrors()) return "numeris";
        service.add(telNr);
        return  "redirect:/list-numeriai";
    }

    // http://localhost:8080/update-numeris/1
    @RequestMapping(value = "/update-numeris/{numerisId}", method = RequestMethod.GET)
    public String showUpdatePage(ModelMap modelMap, @PathVariable Long numerisId){
        modelMap.addAttribute("numeris", service.findNumberById(numerisId));
        return "numeris";
    }

    // http://localhost:8080/update-numeris/1
    @RequestMapping(value = "/update-numeris/{numerisId}", method = RequestMethod.POST)
    public String update(ModelMap modelMap, @ModelAttribute("numeris") TelNr telNr, BindingResult result){
        if (result.hasErrors()){
            return "numeris";
        }
        service.update(telNr);
        return "redirect:/list-numeriai";
    }

    // http://localhost:8080/delete-numeris/1
    @GetMapping("/delete-numeris/{numerisId}")
    public String delete(@PathVariable Long numerisId){
        service.deleteInBothTablesById(numerisId);
        return "redirect:/list-numeriai";
    }

}
