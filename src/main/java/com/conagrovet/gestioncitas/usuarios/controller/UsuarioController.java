package com.conagrovet.gestioncitas.usuarios.controller;


import com.conagrovet.gestioncitas.usuarios.dto.UsuarioDto;
import com.conagrovet.gestioncitas.usuarios.entity.Usuario;
import com.conagrovet.gestioncitas.usuarios.service.UsuariosServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;


@Controller
@RequestMapping("/usuarios")
@Slf4j
public class UsuarioController {

    @Autowired
    private UsuariosServiceImpl service;

    @GetMapping
    public String index(){
        return "/admin/index";
    }

    @GetMapping("/clientes")
    public String listaClientes(Model model){
        var listaClientes = service.listaClientesActivos();
        model.addAttribute("listaUsuarios", listaClientes);

        if (model.containsAttribute("estado")) {
            String mensaje = (String) model.asMap().get("mensaje");
            String estado = (String) model.asMap().get("estado");

            model.addAttribute("mensaje", mensaje);
            model.addAttribute("estado", estado);
            // Hacer algo con el mensaje
        }

        return "/admin/clientes/index";
    }

    @PostMapping("/clientes/store")
    public String clientesStore(
            @RequestParam("nombre") String nombre, @RequestParam("apellido_paterno") String apellido_paterno, @RequestParam("apellido_materno") String apellido_materno,
            @RequestParam("tipo_doc") Integer tipo_doc, @RequestParam("num_doc") String num_doc,@RequestParam("email") String email, @RequestParam("password") String password,
            @RequestParam("telefono") String telefono, @RequestParam("sexo") Character sexo,
            @RequestParam("fecha_nacimiento") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @RequestParam("imagen") MultipartFile imagen, RedirectAttributes redirectAttributes){

        Boolean response = service.guardarUsuario(nombre,apellido_paterno,apellido_materno,tipo_doc,num_doc,email,password,telefono,sexo,fecha,imagen,'A','C');

        if (response){
            redirectAttributes.addFlashAttribute("mensaje", "¡El cliente se ha registrado satisfactoriamente!");
            redirectAttributes.addFlashAttribute("estado", "ok");
        }else {
            redirectAttributes.addFlashAttribute("mensaje", "¡Ha ocurrido un error y no se podido guardar!");
            redirectAttributes.addFlashAttribute("estado", "error");

        }

        return "redirect:/usuarios/clientes";
    }

    @GetMapping("/clientes/eliminar/{id}")
    public String clientesDelete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){

        Boolean response = service.eliminarUsuario(id);

        if (response){
            redirectAttributes.addFlashAttribute("mensaje", "¡El cliente se ha eliminado satisfactoriamente!");
            redirectAttributes.addFlashAttribute("estado", "ok");
        }else {
            redirectAttributes.addFlashAttribute("mensaje", "¡Ha ocurrido un error y no se podido eliminar!");
            redirectAttributes.addFlashAttribute("estado", "error");

        }

        return "redirect:/usuarios/clientes";
    }

    @GetMapping("/veterinarios")
    public String listaVeterinarios(Model model){
        var listaVeterinarios = service.listaVeterinariosActivos();
        model.addAttribute("listaUsuarios", listaVeterinarios);
        return "/admin/veterinarios/index";
    }



    @GetMapping("/administradores")
    public String listaAdministradores(Model model){
        var listaAdministradores = service.listaAdministradoresActivos();
        model.addAttribute("listaUsuarios", listaAdministradores);
        return "/admin/administradores/index";
    }


}
