package com.conagrovet.gestioncitas.citas.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ResponseCitaDto {
    private Integer id;
    private Integer mascota_id;
    private String nombre;
    private String tipo;
    private String raza;
    private Double peso_actual;
    private String color;
    private Character sexo;

    private Character esterilizado;

    private Character estado_mascota;

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
