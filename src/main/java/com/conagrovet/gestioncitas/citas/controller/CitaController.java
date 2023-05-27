package com.conagrovet.gestioncitas.citas.controller;

import com.conagrovet.gestioncitas.citas.dto.ResponseCitaDto;
import com.conagrovet.gestioncitas.citas.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/citas")
public class CitaController {

    @Autowired
    private CitaService citaService;

    @GetMapping
    public String index(Model model){
        List<ResponseCitaDto> listaReponseCitaDto = citaService.getCitas();
        model.addAttribute("listaReponseCitaDto",listaReponseCitaDto);
        return "/citas/index";
    }

    @GetMapping("/{id}")
    public String getCita(@PathVariable("id") String id, Model model){
        ResponseCitaDto reponseCitaDto = citaService.getCita(Integer.parseInt(id));
        model.addAttribute("reponseCitaDto",reponseCitaDto);
        return "/citas/cita";
    }

    @PostMapping("/store")
    public String clientesStore(
            @RequestParam("contexto") String contexto, @RequestParam("detalles") String detalles, @RequestParam("peso") Double peso,
            @RequestParam("f_cardiaca") String f_cardiaca, @RequestParam("f_respiratoria") String f_respiratoria, @RequestParam("mascota_id") String mascota_id,
            @RequestParam("fecha_cita") @DateTimeFormat(pattern = "yyyy-MM-dd H:i:s") Date fecha, RedirectAttributes redirectAttributes){

        Boolean response = citaService.saveCita(Integer.parseInt(mascota_id),contexto,detalles,fecha,'A',peso,f_cardiaca,f_respiratoria);
        if (response){
            redirectAttributes.addFlashAttribute("mensajeOk", "¡Cita  se ha creado correctamente!");
        }else {
            redirectAttributes.addFlashAttribute("mensajeError", "¡Ha ocurrido un error y no se podido crear la cita!");
        }
        return "redirect:/citas";
    }

    @PostMapping("/update/{id}")
    public String updateStore(
            @RequestParam("contexto") String contexto, @RequestParam("detalles") String detalles, @RequestParam("peso") Double peso,
            @RequestParam("f_cardiaca") String f_cardiaca, @RequestParam("f_respiratoria") String f_respiratoria, @RequestParam("mascota_id") String mascota_id,
            @RequestParam("fecha_cita") @DateTimeFormat(pattern = "yyyy-MM-dd H:i:s") Date fecha, RedirectAttributes redirectAttributes, @PathVariable("id") String id){

        Boolean response = citaService.updateCita(Integer.parseInt(id), Integer.parseInt(mascota_id),contexto,detalles,fecha,'A',peso,f_cardiaca,f_respiratoria);
        if (response){
            redirectAttributes.addFlashAttribute("mensajeOk", "¡Cita  se ha actualizado correctamente!");
        }else {
            redirectAttributes.addFlashAttribute("mensajeError", "¡Ha ocurrido un error y no se podido actualizar la cita!");
        }
        return "redirect:/citas";
    }
}
