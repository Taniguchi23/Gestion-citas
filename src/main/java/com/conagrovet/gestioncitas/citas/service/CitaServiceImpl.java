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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CitaServiceImpl implements CitaService{
    @Autowired
    private CitasRepository citasRepository;

    @Autowired
    private MascotaRepository mascotaRepository;
    @Override
    public List<ResponseCitaDto> getCitas() {


        List<Cita> listaCitasPendiente = citasRepository.findPendienteCitasOrderByFechaCitaDesc();
        List<Cita> listaCitas = new ArrayList<>(listaCitasPendiente);
        List<Cita> listaCitasNoPendiente = citasRepository.findNoPendienteCitasOrderByFechaCitaDesc();
        listaCitas.addAll(listaCitasNoPendiente);
        List<ResponseCitaDto> listaCitasDto = new ArrayList<>();
        for (Cita c : listaCitas ) {
            Mascota m = mascotaRepository.findFirstById(c.getMascota_id());
            ResponseCitaDto responseCitaDto = Util.mapCitaToCitaDto(c, m);

        listaCitasDto.add(responseCitaDto);
        }
        return listaCitasDto;
    }

    @Override
    public List<ResponseCitaDto> getCitasById(Integer id) {

        List<Cita> listaCitasPendiente = citasRepository.findPendienteCitasOrderByFechaCitaDescById(id);
        List<Cita> listaCitas = new ArrayList<>(listaCitasPendiente);
        List<Cita> listaCitasNoPendiente = citasRepository.findNoPendienteCitasOrderByFechaCitaDescById(id);
        listaCitas.addAll(listaCitasNoPendiente);
        List<ResponseCitaDto> listaCitasDto = new ArrayList<>();
        for (Cita c : listaCitas ) {
            Mascota m = mascotaRepository.findFirstById(c.getMascota_id());
            ResponseCitaDto responseCitaDto = Util.mapCitaToCitaDto(c, m);

            listaCitasDto.add(responseCitaDto);
        }
        return listaCitasDto;
    }



    @Override
    public ResponseCitaDto getCita(Integer id) {
        Cita cita = citasRepository.findFirstById(id);
        Mascota m = mascotaRepository.findFirstById(cita.getMascota_id());
        ResponseCitaDto responseCitaDto = Util.mapCitaToCitaDto(cita,m);
        return responseCitaDto;
    }

    @Override
    public Boolean saveCita(Integer mascota_id, String contexto, String detalles, Date fecha_cita, Character estado, Double peso, String f_cardiaca, String f_respiratoria) {
       try {
           Cita cita = new Cita();
           cita.setMascota_id(mascota_id);
           cita.setContexto(contexto);
           cita.setDetalles(detalles);
           cita.setFecha_cita(fecha_cita);
           cita.setEstado('P');
           cita.setPeso(peso);
           cita.setF_cardiaca(f_cardiaca);
           cita.setF_respiratoria(f_respiratoria);
           citasRepository.save(cita);
           return  true;
       }catch (Exception e){
           return  false;
       }
    }

    @Override
    public Boolean updateCita(Integer id, Integer mascota_id, String contexto, String detalles, Date fecha_cita, Character estado, Double peso, String f_cardiaca, String f_respiratoria) {
        try {
            Cita cita = citasRepository.findFirstById(id);
            cita.setMascota_id(mascota_id);
            cita.setContexto(contexto);
            cita.setDetalles(detalles);
            cita.setFecha_cita(fecha_cita);
            cita.setEstado(estado);
            cita.setPeso(peso);
            cita.setF_cardiaca(f_cardiaca);
            cita.setF_respiratoria(f_respiratoria);
            citasRepository.save(cita);
            return  true;
        }catch (Exception e){
            return  false;
        }
    }


}
