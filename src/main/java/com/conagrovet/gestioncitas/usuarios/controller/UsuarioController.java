package com.conagrovet.gestioncitas.usuarios.controller;

import com.conagrovet.gestioncitas.usuarios.entity.Usuario;
import com.conagrovet.gestioncitas.usuarios.service.UsuariosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
@Slf4j
public class UsuarioController {
   // @Autowired
   // private UsuariosService usuariosService;
    @GetMapping
    public String index(Model model){
      /*  model.addAttribute("listaUsuarios",usuariosService.listaUsuarios());
        log.info("bndsadsd");
        log.error("lista", usuariosService.listaUsuarios());*/
        return "admin/usuario/index";
    }

    @GetMapping("/prueba")
    public String prueba(Model model){
     /*   log.info("hhhhhhhhhhhhh");
        List<Usuario> lista = usuariosService.listaVeterinario();
        for (Usuario u :lista){
            log.info("usuario" , u.getNombre() );
        }*/

        return "admin/usuario/index";
    }
}
