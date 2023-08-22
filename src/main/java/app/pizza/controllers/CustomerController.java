package app.pizza.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/secciones")
@AllArgsConstructor
public class CustomerController {
    @GetMapping("")
    public String getCostumer(){
        return "hola";
    }
}
