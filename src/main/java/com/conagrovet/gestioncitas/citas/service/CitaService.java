package com.conagrovet.gestioncitas.citas.service;

import com.conagrovet.gestioncitas.citas.dto.ResponseCitaDto;

import java.util.List;

public interface CitaService {
    List<ResponseCitaDto> getCitas();
}
