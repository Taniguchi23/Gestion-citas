package com.conagrovet.gestioncitas.usuarios.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;
@Data
public class UsuarioDto {
    private Integer id;
    private String nombre;

    private String apellido_materno;

    private String apellido_paterno;
    private Integer tipo_doc;
    private Character rol;
    private String num_doc;
    private String telefono;
    private Character sexo;
    private String email;
    private Character estado;
    private String imagen;
    private Date fecha_nacimiento;
    private String fecha_nacimiento_string;
}
