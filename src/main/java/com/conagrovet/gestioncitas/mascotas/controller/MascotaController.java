package com.conagrovet.gestioncitas.mascotas.controller;

import com.conagrovet.gestioncitas.mascotas.service.MascotasService;
import com.conagrovet.gestioncitas.mascotas.service.MascotasServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
@Slf4j
@RequestMapping("/mascotas")
public class MascotaController {
    @Autowired
    private MascotasService mascotasService;

    @GetMapping
    public String index(Model model){
        var listaMascotas = mascotasService.getListMascota();
        model.addAttribute("listaMascota", listaMascotas);
        return "/veterinario/mascota/index";
    }

    @PostMapping("/store")
    public String clientesStore(
            @RequestParam("nombre") String nombre, @RequestParam("color") String color, @RequestParam("tipo") String tipo, @RequestParam("peso") Double peso,
            @RequestParam("raza") String raza, @RequestParam("esterilizado") Character esterilizado,@RequestParam("usuario_id") Integer usuario_id,
            @RequestParam("sexo") Character sexo,
            @RequestParam("fecha_nacimiento") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @RequestParam("imagen") MultipartFile imagen, RedirectAttributes redirectAttributes){

        Boolean response = mascotasService.saveMascota(nombre, usuario_id, color,tipo,raza,sexo,esterilizado,'A',imagen,fecha, peso);
        if (response){
            redirectAttributes.addFlashAttribute("mensajeOk", "¡ "+ nombre+ "  ahora pertenece a Conagrovet!");
        }else {
            redirectAttributes.addFlashAttribute("mensajeError", "¡Ha ocurrido un error y no se podido guardar!");
        }


        return "redirect:/mascotas";
    }
}
