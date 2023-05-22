package com.conagrovet.gestioncitas.usuarios.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.context.annotation.Configuration;


import java.util.Date;


@Entity
@Table(name = "usuarios")
@Data
public class Usuario  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nombre;

    private String apellido_materno;

    private String apellido_paterno;
    @Column(columnDefinition = "INTEGER default 1")
    private Integer tipo_doc;
    @Column(columnDefinition = "CHAR(1) default 'C'")
    private Character rol;
    private String num_doc;
    private String email;
    private String password;
    @Column(columnDefinition = "CHAR(1) default 'A'")
    private Character estado;
    @Column(nullable = true)
    private String imagen;
    @Column(nullable = true)
    private String telefono;
    private Character sexo;
    @Column(nullable = true)
    private Date fecha_nacimiento;
    @Column(nullable = true)
    private Date created_at;
    @Column(nullable = true)
    private Date updated_at;
    @Column(nullable = true)
    private Integer created_user;
    @Column(nullable = true)
    private Integer updated_user;


}
