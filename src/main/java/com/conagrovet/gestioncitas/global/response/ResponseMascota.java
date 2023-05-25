package com.conagrovet.gestioncitas.global.response;

import com.conagrovet.gestioncitas.mascotas.dto.MascotaResponseDto;
import lombok.Data;

@Data
public class ResponseMascota {
    private String estado;
    private MascotaResponseDto mascotaResponseDto;
}
