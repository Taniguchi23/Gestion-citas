package com.conagrovet.gestioncitas.mascotas.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "mascotas")
@Data
public class Mascota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;
    private Integer usuario_id;
    private String tipo;
    private String raza;
    private String color;
    private Character sexo;
    private Double peso;
    @Column(nullable = true)
    private String imagen;
    @Column(nullable = true)
    private Character esterilizado;
    @Column(columnDefinition = "CHAR(1) default 'A'")
    private Character estado;
    @Column(nullable = true)
    private Date fecha_nacimiento;
    @Column(nullable = true)
    private Timestamp created_at;
    @Column(nullable = true)
    private Timestamp updated_at;
    @Column(nullable = true)
    private Integer created_user;
    @Column(nullable = true)
    private Integer updated_user;

}
