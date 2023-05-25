package com.conagrovet.gestioncitas.global.controller;

import com.conagrovet.gestioncitas.global.Cuerpo.Cuerpo;
import com.conagrovet.gestioncitas.global.helpers.Util;
import com.conagrovet.gestioncitas.global.response.AjaxResponse;
import com.conagrovet.gestioncitas.global.response.ResponseUsuario;
import com.conagrovet.gestioncitas.mascotas.dto.MascotaResponseDto;
import com.conagrovet.gestioncitas.mascotas.entity.Mascota;
import com.conagrovet.gestioncitas.mascotas.service.MascotasService;
import com.conagrovet.gestioncitas.usuarios.dto.UsuarioDto;
import com.conagrovet.gestioncitas.usuarios.entity.Usuario;
import com.conagrovet.gestioncitas.usuarios.service.UsuariosService;
import com.conagrovet.gestioncitas.usuarios.service.UsuariosServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private UsuariosService usuariosService;

    @Autowired
    private MascotasService mascotasService;
    @GetMapping("/usuarios/existe/{documento}")
    public AjaxResponse obtenerDatos(@PathVariable("documento") String documento) {

       AjaxResponse response = new AjaxResponse();
       boolean existeUsuario = usuariosService.findByDocumento(documento);
       if (existeUsuario){
           response.setEstado("ok");
       }else{
           response.setEstado("error");
       }
        return response;
    }

    @GetMapping("/usuarios/edit/{id}")
    public ResponseUsuario obtenerUsuario(@PathVariable("id") String id) {
        log.error(id);
        ResponseUsuario response = new ResponseUsuario();
        Usuario usuario = usuariosService.findUsuarioById(Integer.parseInt(id));
        if (usuario != null){
            UsuarioDto usuarioDto = Util.mapUsuarioToUsuarioDto(usuario);
            response.setEstado("ok");
            response.setUsuarioDto(usuarioDto);
        }else {
            response.setEstado("error");
        }
        return response;
    }
    @GetMapping("/mascotas/edit/{id}")
    public AjaxResponse obtenerMascota(@PathVariable("id") String id) {
        log.error(id);
        AjaxResponse response = new AjaxResponse();
        MascotaResponseDto mascotaResponseDto = new MascotaResponseDto();
        mascotaResponseDto = mascotasService.getMascota(Integer.parseInt(id));
        return response;
    }
    
}
