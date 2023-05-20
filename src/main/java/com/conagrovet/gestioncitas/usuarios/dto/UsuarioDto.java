package com.conagrovet.gestioncitas.usuarios.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class UsuarioDto {
    public String nombre;
    @Column(name = "apellidoPaterno")
    public String apellidoPaterno;
    @Column(name = "apellidoMaterno")
    public String apellidoMaterno;
    public String email;

}
