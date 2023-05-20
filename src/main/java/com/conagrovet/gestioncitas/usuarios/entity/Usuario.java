package com.conagrovet.gestioncitas.usuarios.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    private String apellidoMaterno;

    private String apellidoPaterno;
    private Integer tipoDoc;
    private String numDoc;
    private String email;
    private String password;
    private Character estado;
    private String imagen;
    private Date fecha_nacimiento;
    private Date created_at;
    private Date updated_at;
    private Integer created_user;
    private Integer updated_user;


}
