package com.conagrovet.gestioncitas.mascotas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @GetMapping
    public String index(){
        return "/usuario/index";
    }
    @GetMapping("/mascotas")
    public String listaMascotas(){
        
    }
}
