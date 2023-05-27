package com.conagrovet.gestioncitas.mascotas.controller;

import com.conagrovet.gestioncitas.citas.dto.ResponseCitaDto;
import com.conagrovet.gestioncitas.citas.service.CitaService;
import com.conagrovet.gestioncitas.mascotas.dto.MascotaResponseDto;
import com.conagrovet.gestioncitas.mascotas.service.MascotasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.List;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
    @Autowired
    private MascotasService mascotasService;

    @Autowired
    private CitaService citaService;

    @GetMapping
    public String index(){
        return "/usuario/index";
    }

    @GetMapping("/mascotas")
    public String listaMascotas(@SessionAttribute("authId") Integer authId, Model model){

        List<MascotaResponseDto> listaMascotaResponseDto = mascotasService.getListMascotaById(authId);
        model.addAttribute("listaMascotas", listaMascotaResponseDto);
        return "/usuario/mascota/index";

    }
    @GetMapping("/mascotas/ver/{id}")
    public String listaMascotas(@PathVariable("id") String id, Model model){
        MascotaResponseDto mascotaResponseDto = mascotasService.getMascota(Integer.parseInt(id));
        model.addAttribute("mascotaDto", mascotaResponseDto);
        return "/usuario/mascota/mascota";
    }
    @GetMapping("/mascotas/cita/{id}")
    public String getCita(@PathVariable("id") String id, Model model){
        ResponseCitaDto reponseCitaDto = citaService.getCita(Integer.parseInt(id));
        model.addAttribute("reponseCitaDto",reponseCitaDto);
        return "/usuario/mascota/cita";
    }
}
