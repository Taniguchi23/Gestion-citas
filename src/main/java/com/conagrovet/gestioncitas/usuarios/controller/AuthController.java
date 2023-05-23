package com.conagrovet.gestioncitas.usuarios.controller;

import com.conagrovet.gestioncitas.usuarios.dto.ResponseLoginDto;
import com.conagrovet.gestioncitas.usuarios.service.AuthServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Slf4j
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthServiceImpl service;
    @PostMapping("/login" )
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, RedirectAttributes redirectAttributes, HttpSession session) {
        log.info("login entro");
        ResponseLoginDto responseLoginDto =  service.login(email,password);
        if (responseLoginDto.getEstado().equals("ok")){
            session.setAttribute("authId", responseLoginDto.getUsuario().getId());
            session.setAttribute("authNombre", responseLoginDto.getUsuario().getNombre());
            session.setAttribute("authImagen", responseLoginDto.getUsuario().getImagen());
            session.setAttribute("authRol", responseLoginDto.getUsuario().getRol());
            session.setAttribute("authEstado", responseLoginDto.getUsuario().getEstado());
            log.info(""+responseLoginDto.getUsuario().getRol());
            if (responseLoginDto.getUsuario().getRol().equals('C')){
                return "redirect:/clientes";
            } else if (responseLoginDto.getUsuario().getRol().equals('V')){
                return "redirect:/veterinarios";
            } else {
                return "redirect:/usuarios";
            }
        }else {
            redirectAttributes.addFlashAttribute("mensajeError", responseLoginDto.getMensaje());
        }
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes){
        session.invalidate();
        redirectAttributes.addFlashAttribute("mensajeOk","Usted se ha desconectado satisfactoriamente");
        return "redirect:/";
    }

}
