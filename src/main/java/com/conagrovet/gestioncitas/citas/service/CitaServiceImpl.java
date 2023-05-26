package com.conagrovet.gestioncitas.citas.service;

import com.conagrovet.gestioncitas.citas.dto.ResponseCitaDto;
import com.conagrovet.gestioncitas.citas.entity.Cita;
import com.conagrovet.gestioncitas.citas.repository.CitasRepository;
import com.conagrovet.gestioncitas.global.helpers.Util;
import com.conagrovet.gestioncitas.mascotas.dto.MascotaResponseDto;
import com.conagrovet.gestioncitas.mascotas.entity.Mascota;
import com.conagrovet.gestioncitas.mascotas.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CitaServiceImpl implements CitaService{
    @Autowired
    private CitasRepository citasRepository;

    @Autowired
    private MascotaRepository mascotaRepository;
    @Override
    public List<ResponseCitaDto> getCitas() {

        List<Cita> listaCitas = citasRepository.findAll();
        List<ResponseCitaDto> listaCitasDto = new ArrayList<>();

       /* List<Mascota> listaMascotas = mascotaRepository.findMascotas();
        List<MascotaResponseDto> listaMascotasDto = new ArrayList<>();
        MascotaResponseDto mascotaDto = new MascotaResponseDto();*/

        for (Cita c : listaCitas ) {
            Mascota m = mascotaRepository.findFirstById(c.getMascota_id());
            ResponseCitaDto responseCitaDto = Util.mapCitaToCitaDto(c, m);

        listaCitasDto.add(responseCitaDto);
        }
        return listaCitasDto;
    }
}
