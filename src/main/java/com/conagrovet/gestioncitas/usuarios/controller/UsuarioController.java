package com.conagrovet.gestioncitas.usuarios.controller;

import com.conagrovet.gestioncitas.usuarios.service.UsuariosService;
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
import java.util.List;


@Controller
@RequestMapping("/usuarios")
@Slf4j
public class UsuarioController {

    @Autowired
    private UsuariosService usuariosService;

    @GetMapping
    public String index(){
        return "/admin/index";
    }



    @GetMapping("/clientes")
    public String listaClientes(Model model){
        var listaClientes = usuariosService.listaClientesActivos();
        model.addAttribute("listaUsuarios", listaClientes);
        return "/admin/clientes/index";
    }

    @PostMapping("/clientes/store")
    public String clientesStore(
            @RequestParam("nombre") String nombre, @RequestParam("apellido_paterno") String apellido_paterno, @RequestParam("apellido_materno") String apellido_materno,
            @RequestParam("tipo_doc") Integer tipo_doc, @RequestParam("num_doc") String num_doc,@RequestParam("email") String email, @RequestParam("password") String password,
            @RequestParam("telefono") String telefono, @RequestParam("sexo") Character sexo,
            @RequestParam("fecha_nacimiento") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @RequestParam("imagen") MultipartFile imagen, RedirectAttributes redirectAttributes){

        List<String> listaMensaje = usuariosService.verificarUsuario(email,num_doc, "S",0);
        if (listaMensaje.size() > 0){
            for (String mensaje: listaMensaje ){
                if (mensaje.equals("email")){
                    redirectAttributes.addFlashAttribute("mensajeEmail", "El email  "+email+" ya ha sido registrado");
                }else {
                    redirectAttributes.addFlashAttribute("mensajeDocumento", "El documento "+num_doc+" ya ha sido registrado");
                }
            }

        }else {
            Boolean response = usuariosService.guardarUsuario(nombre,apellido_paterno,apellido_materno,tipo_doc,num_doc,email,password,telefono,sexo,fecha,imagen,'A','C');

            if (response){
                redirectAttributes.addFlashAttribute("mensajeOk", "¡El cliente se ha registrado satisfactoriamente!");
            }else {
                redirectAttributes.addFlashAttribute("mensajeError", "¡Ha ocurrido un error y no se podido guardar!");
            }
        }

        return "redirect:/usuarios/clientes";
    }

    @GetMapping("/clientes/eliminar/{id}")
    public String clientesDelete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){

        Boolean response = usuariosService.eliminarUsuario(id);
        if (response){
            redirectAttributes.addFlashAttribute("mensaje", "¡El cliente se ha eliminado satisfactoriamente!");
            redirectAttributes.addFlashAttribute("estado", "ok");
        }else {
            redirectAttributes.addFlashAttribute("mensaje", "¡Ha ocurrido un error y no se podido eliminar!");
            redirectAttributes.addFlashAttribute("estado", "error");
        }
        return "redirect:/usuarios/clientes";
    }
    @PostMapping("/clientes/update/{id}")
    public String clientesUpdate(
            @RequestParam("nombre") String nombre, @RequestParam("apellido_paterno") String apellido_paterno, @RequestParam("apellido_materno") String apellido_materno,
            @RequestParam("tipo_doc") Integer tipo_doc, @RequestParam("num_doc") String num_doc,@RequestParam("email") String email, @RequestParam("password") String password,
            @RequestParam("telefono") String telefono, @RequestParam("sexo") Character sexo,@RequestParam("rol") Character rol,@RequestParam("estado") Character estado,
            @RequestParam("fecha_nacimiento") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @RequestParam("imagen") MultipartFile imagen, RedirectAttributes redirectAttributes,@PathVariable("id") String id){

        List<String> listaMensaje = usuariosService.verificarUsuario(email,num_doc,"U",Integer.parseInt(id));
        if (listaMensaje.size() > 0){
            for (String mensaje: listaMensaje ){
                if (mensaje.equals("email")){
                    redirectAttributes.addFlashAttribute("mensajeEmail", "El email  "+email+" ya ha sido registrado");
                }else {
                    redirectAttributes.addFlashAttribute("mensajeDocumento", "El documento "+num_doc+" ya ha sido registrado");
                }
            }

        }else {
            Boolean response = usuariosService.actualizarUsuario(nombre,apellido_paterno,apellido_materno,tipo_doc,num_doc,email,password,telefono,sexo,fecha,imagen,estado,rol,Integer.parseInt(id));

            if (response){
                redirectAttributes.addFlashAttribute("mensajeOk", "¡El cliente se ha actualizado satisfactoriamente!");
            }else {
                redirectAttributes.addFlashAttribute("mensajeError", "¡Ha ocurrido un error y no se podido actualizar!");
            }
        }

        return "redirect:/usuarios/clientes";
    }


    @GetMapping("/veterinarios")
    public String listaVeterinarios(Model model){

        var listaVeterinarios = usuariosService.listaVeterinariosActivos();

        model.addAttribute("listaUsuarios", listaVeterinarios);
        return "/admin/veterinarios/index";
    }
    @PostMapping("/veterinarios/store")
    public String veterinariosStore(
            @RequestParam("nombre") String nombre, @RequestParam("apellido_paterno") String apellido_paterno, @RequestParam("apellido_materno") String apellido_materno,
            @RequestParam("tipo_doc") Integer tipo_doc, @RequestParam("num_doc") String num_doc,@RequestParam("email") String email, @RequestParam("password") String password,
            @RequestParam("telefono") String telefono, @RequestParam("sexo") Character sexo,
            @RequestParam("fecha_nacimiento") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @RequestParam("imagen") MultipartFile imagen, RedirectAttributes redirectAttributes){

        List<String> listaMensaje = usuariosService.verificarUsuario(email,num_doc, "S",0);
        if (listaMensaje.size() > 0){
            for (String mensaje: listaMensaje ){
                if (mensaje.equals("email")){
                    redirectAttributes.addFlashAttribute("mensajeEmail", "El email  "+email+" ya ha sido registrado");
                }else {
                    redirectAttributes.addFlashAttribute("mensajeDocumento", "El documento "+num_doc+" ya ha sido registrado");
                }
            }

        }else {
            Boolean response = usuariosService.guardarUsuario(nombre,apellido_paterno,apellido_materno,tipo_doc,num_doc,email,password,telefono,sexo,fecha,imagen,'A','V');

            if (response){
                redirectAttributes.addFlashAttribute("mensajeOk", "¡El veterinario se ha registrado satisfactoriamente!");
            }else {
                redirectAttributes.addFlashAttribute("mensajeError", "¡Ha ocurrido un error y no se podido guardar!");
            }
        }

        return "redirect:/usuarios/veterinarios";
    }
    @GetMapping("/veterinarios/eliminar/{id}")
    public String veterinariosDelete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){

        Boolean response = usuariosService.eliminarUsuario(id);

        if (response){
            redirectAttributes.addFlashAttribute("mensajeOk", "¡El veterinario se ha eliminado satisfactoriamente!");
        }else {
            redirectAttributes.addFlashAttribute("mensajeError", "¡Ha ocurrido un error y no se podido eliminar!");

        }

        return "redirect:/usuarios/veterinarios";
    }
    @PostMapping("/veterinarios/update/{id}")
    public String veterinariosUpdate(
            @RequestParam("nombre") String nombre, @RequestParam("apellido_paterno") String apellido_paterno, @RequestParam("apellido_materno") String apellido_materno,
            @RequestParam("tipo_doc") Integer tipo_doc, @RequestParam("num_doc") String num_doc,@RequestParam("email") String email, @RequestParam("password") String password,
            @RequestParam("telefono") String telefono, @RequestParam("sexo") Character sexo,@RequestParam("rol") Character rol,@RequestParam("estado") Character estado,
            @RequestParam("fecha_nacimiento") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @RequestParam("imagen") MultipartFile imagen, RedirectAttributes redirectAttributes,@PathVariable("id") String id){

        List<String> listaMensaje = usuariosService.verificarUsuario(email,num_doc,"U",Integer.parseInt(id));
        if (listaMensaje.size() > 0){
            for (String mensaje: listaMensaje ){
                if (mensaje.equals("email")){
                    redirectAttributes.addFlashAttribute("mensajeEmail", "El email  "+email+" ya ha sido registrado");
                }else {
                    redirectAttributes.addFlashAttribute("mensajeDocumento", "El documento "+num_doc+" ya ha sido registrado");
                }
            }

        }else {
            Boolean response = usuariosService.actualizarUsuario(nombre,apellido_paterno,apellido_materno,tipo_doc,num_doc,email,password,telefono,sexo,fecha,imagen,estado,rol,Integer.parseInt(id));

            if (response){
                redirectAttributes.addFlashAttribute("mensajeOk", "¡El veterinario se ha actualizado satisfactoriamente!");
            }else {
                redirectAttributes.addFlashAttribute("mensajeError", "¡Ha ocurrido un error y no se podido actualizar!");
            }
        }

        return "redirect:/usuarios/veterinarios";
    }


    @GetMapping("/administradores")
    public String listaAdministradores(Model model){
        var listaAdministradores = usuariosService.listaAdministradoresActivos();
        model.addAttribute("listaUsuarios", listaAdministradores);
        return "/admin/administradores/index";
    }
    @PostMapping("/administradores/store")
    public String administradoresStore(
            @RequestParam("nombre") String nombre, @RequestParam("apellido_paterno") String apellido_paterno, @RequestParam("apellido_materno") String apellido_materno,
            @RequestParam("tipo_doc") Integer tipo_doc, @RequestParam("num_doc") String num_doc,@RequestParam("email") String email, @RequestParam("password") String password,
            @RequestParam("telefono") String telefono, @RequestParam("sexo") Character sexo,
            @RequestParam("fecha_nacimiento") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @RequestParam("imagen") MultipartFile imagen, RedirectAttributes redirectAttributes){

        List<String> listaMensaje = usuariosService.verificarUsuario(email,num_doc, "S", 0);
        if (listaMensaje.size() > 0){
            for (String mensaje: listaMensaje ){
                if (mensaje.equals("email")){
                    redirectAttributes.addFlashAttribute("mensajeEmail", "El email  "+email+" ya ha sido registrado");
                }else {
                    redirectAttributes.addFlashAttribute("mensajeDocumento", "El documento "+num_doc+" ya ha sido registrado");
                }
            }

        }else {
            Boolean response = usuariosService.guardarUsuario(nombre,apellido_paterno,apellido_materno,tipo_doc,num_doc,email,password,telefono,sexo,fecha,imagen,'A','A');

            if (response){
                redirectAttributes.addFlashAttribute("mensajeOk", "¡El administrador se ha registrado satisfactoriamente!");
            }else {
                redirectAttributes.addFlashAttribute("mensajeError", "¡Ha ocurrido un error y no se podido guardar!");
            }
        }

        return "redirect:/usuarios/administradores";
    }
    @GetMapping("/administradores/eliminar/{id}")
    public String administradoresDelete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes){

        Boolean response = usuariosService.eliminarUsuario(id);

        if (response){
            redirectAttributes.addFlashAttribute("mensajeOk", "¡El Administrador se ha eliminado satisfactoriamente!");
        }else {
            redirectAttributes.addFlashAttribute("mensajeError", "¡Ha ocurrido un error y no se podido eliminar!");

        }

        return "redirect:/usuarios/administradores";
    }
    @PostMapping("/administradores/update/{id}")
    public String administradoresUpdate(
            @RequestParam("nombre") String nombre, @RequestParam("apellido_paterno") String apellido_paterno, @RequestParam("apellido_materno") String apellido_materno,
            @RequestParam("tipo_doc") Integer tipo_doc, @RequestParam("num_doc") String num_doc,@RequestParam("email") String email, @RequestParam("password") String password,
            @RequestParam("telefono") String telefono, @RequestParam("sexo") Character sexo,@RequestParam("rol") Character rol,@RequestParam("estado") Character estado,
            @RequestParam("fecha_nacimiento") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fecha, @RequestParam("imagen") MultipartFile imagen, RedirectAttributes redirectAttributes,@PathVariable("id") String id){

        List<String> listaMensaje = usuariosService.verificarUsuario(email,num_doc,"U",Integer.parseInt(id));
        if (listaMensaje.size() > 0){
            for (String mensaje: listaMensaje ){
                if (mensaje.equals("email")){
                    redirectAttributes.addFlashAttribute("mensajeEmail", "El email  "+email+" ya ha sido registrado");
                }else {
                    redirectAttributes.addFlashAttribute("mensajeDocumento", "El documento "+num_doc+" ya ha sido registrado");
                }
            }

        }else {
            Boolean response = usuariosService.actualizarUsuario(nombre,apellido_paterno,apellido_materno,tipo_doc,num_doc,email,password,telefono,sexo,fecha,imagen,estado,rol,Integer.parseInt(id));

            if (response){
                redirectAttributes.addFlashAttribute("mensajeOk", "¡El administrador se ha actualizado satisfactoriamente!");
            }else {
                redirectAttributes.addFlashAttribute("mensajeError", "¡Ha ocurrido un error y no se podido actualizar!");
            }
        }

        return "redirect:/usuarios/administradores";
    }


}
