package com.conagrovet.gestioncitas.global.response;

import com.conagrovet.gestioncitas.mascotas.dto.MascotaResponseDto;
import lombok.Data;

import java.util.List;

@Data
public class ResponseListaMascota {
    private String estado;
    private String estado_lista;
    private List<MascotaResponseDto> lista_mascota;

}
