package com.conagrovet.gestioncitas.mascotas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/veterinarios")
public class VeterinarioController {
    @GetMapping
    public String index(){
        return "/veterinario/index";
    }
}
