package com.conagrovet.gestioncitas.mascotas.service;

import com.conagrovet.gestioncitas.mascotas.dto.MascotaResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface MascotasService {
    List<MascotaResponseDto> getListMascota();
    Boolean saveMascota(String nombre, Integer usuario_id, String color, String tipo, String raza, Character sexo, Character esterilizado, Character estado, MultipartFile imagen, Date fecha_nacimiento,Double peso);

    MascotaResponseDto getMascota(Integer id);


}
