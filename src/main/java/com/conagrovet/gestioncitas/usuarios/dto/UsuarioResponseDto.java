package com.conagrovet.gestioncitas.usuarios.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UsuarioResponseDto {
    private Integer id;
    private String nombre_completo;
    private String email;
}
