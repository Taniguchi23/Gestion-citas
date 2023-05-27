package com.conagrovet.gestioncitas.citas.service;

import com.conagrovet.gestioncitas.citas.dto.ResponseCitaDto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface CitaService {
    List<ResponseCitaDto> getCitas();
    List<ResponseCitaDto> getCitasById(Integer id);
    ResponseCitaDto getCita(Integer id);
    Boolean saveCita(Integer mascota_id, String contexto, String detalles, Date fecha_cita, Character estado, Double peso, String f_cardiaca, String f_respiratoria);
    Boolean updateCita(Integer id,Integer mascota_id, String contexto, String detalles, Date fecha_cita, Character estado, Double peso, String f_cardiaca, String f_respiratoria);


}
