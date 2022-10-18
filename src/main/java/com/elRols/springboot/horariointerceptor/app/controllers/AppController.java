package com.elRols.springboot.horariointerceptor.app.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Controller
public class AppController {

    @Value("${config.hours.opening}")
    private Integer opeinig;
    @Value("${config.hours.closing}")
    private Integer closing;

    @GetMapping({"/", "/index"})
    private String index(Model model) {
        Map<String, String> attributes = new HashMap<>();
        attributes.put("tittle", "Welcome to customer services");

        model.addAllAttributes(attributes);
        return "index";
    }

    @GetMapping("/cerrado")
    public String cerrado(Model model) {
        StringBuilder message = new StringBuilder("Closed, please come here in working hours: ");
        message.append( "from " + opeinig);
        message.append(" to " + closing);

        model.addAttribute("tittle", "Out of working hoursssss");
        model.addAttribute("msj", message.toString());
        return "cerrado";
    }

}
