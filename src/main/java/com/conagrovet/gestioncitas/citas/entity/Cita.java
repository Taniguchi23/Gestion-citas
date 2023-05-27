package com.conagrovet.gestioncitas.citas.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "citas")
@Data
public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer mascota_id;
    private String contexto;
    private String detalles;
    private Date fecha_cita;
    private Character estado;
    private Double peso;
    private String f_cardiaca;
    private String f_respiratoria;
    @Column(nullable = true)
    private Date created_at;
    @Column(nullable = true)
    private Date updated_at;
    @Column(nullable = true)
    private Integer created_user;
    @Column(nullable = true)
    private Integer updated_user;
}
