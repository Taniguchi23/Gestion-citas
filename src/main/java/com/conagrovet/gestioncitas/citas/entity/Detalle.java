package com.conagrovet.gestioncitas.citas.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "detalles")
@Data
public class Detalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer cita_id;
    private String descripcion;
}
