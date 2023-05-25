package com.conagrovet.gestioncitas.mascotas.dto;

import com.conagrovet.gestioncitas.usuarios.dto.UsuarioDto;
import com.conagrovet.gestioncitas.usuarios.dto.UsuarioResponseDto;
import com.conagrovet.gestioncitas.usuarios.entity.Usuario;

import lombok.Data;

import java.util.Date;

@Data
public class MascotaResponseDto {
    private Integer id;
    private String nombre;
    private UsuarioResponseDto usuario;
    private String tipo;
    private String raza;
    private Double peso;
    private String color;
    private Character sexo;

    private String imagen;

    private Character esterilizado;

    private Character estado;

    private Date fecha_nacimiento;
}
