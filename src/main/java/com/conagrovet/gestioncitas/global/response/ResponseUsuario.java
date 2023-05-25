package com.conagrovet.gestioncitas.global.response;

import com.conagrovet.gestioncitas.usuarios.dto.UsuarioDto;
import lombok.Data;

@Data
public class ResponseUsuario {
    private  String estado;
    private UsuarioDto usuarioDto;
}
