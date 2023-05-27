package com.conagrovet.gestioncitas.citas.dto;

import lombok.Data;

import java.util.Date;
@Data
public class CitaDto {

    private Integer id;
    private Integer mascota_id;
    private String nombre;
    private String nombre_mascota;
    private String tipo;
    private String raza;
    private Double peso_actual;
    private String color;
    private String sexo;

    private String esterilizado;

    private String estado_mascota;

    private Date fecha_nacimiento;
    private String fecha_nacimiento_string ;



    private String contexto;
    private String detalles;
    private Date fecha_cita;
    private String fecha_cita_string;
    private Character estado;
    private Double peso;
    private String f_cardiaca;
    private String f_respiratoria;
}
