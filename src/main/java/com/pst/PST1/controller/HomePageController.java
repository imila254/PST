package com.pst.PST1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomePageController {

    @GetMapping("/")
    public String showRootPage(ModelMap model) {
        model.put("name", "ANONYMOUS");
        return "home"; // view resolver
    }

    @GetMapping("/home") //
    public String showWelcomePage(@RequestParam String name, ModelMap model) {
        model.put("name", name);
        return "home"; // view resolver
    }

}
