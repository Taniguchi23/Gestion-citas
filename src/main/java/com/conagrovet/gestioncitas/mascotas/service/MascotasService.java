package com.conagrovet.gestioncitas.mascotas.service;

import com.conagrovet.gestioncitas.mascotas.dto.MascotaResponseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

public interface MascotasService {
    List<MascotaResponseDto> getListMascota();
    List<MascotaResponseDto> getListMascotaById(Integer id);
    Boolean saveMascota(String nombre, String usuario_id, String color, String tipo, String raza, Character sexo, Character esterilizado, Character estado, MultipartFile imagen, Date fecha_nacimiento,Double peso);
    Boolean updateMascota(String nombre, String num_doc, String color, String tipo, String raza, Character sexo, Character esterilizado, Character estado, MultipartFile imagen, Date fecha_nacimiento,Double peso,Integer id);
    MascotaResponseDto getMascota(Integer id);

    Boolean eliminarMascota(Integer id);
}
