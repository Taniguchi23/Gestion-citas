package com.conagrovet.gestioncitas.usuarios.dto;

import com.conagrovet.gestioncitas.usuarios.entity.Usuario;
import lombok.Data;

@Data
public class ResponseLoginDto {
    private  String estado;
    private  String mensaje;
    private Usuario usuario;
}
