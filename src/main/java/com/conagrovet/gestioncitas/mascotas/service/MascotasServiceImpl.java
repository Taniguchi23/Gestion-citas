package com.conagrovet.gestioncitas.mascotas.service;

import com.conagrovet.gestioncitas.mascotas.dto.MascotaResponseDto;
import com.conagrovet.gestioncitas.mascotas.entity.Mascota;
import com.conagrovet.gestioncitas.mascotas.repository.MascotaRepository;
import com.conagrovet.gestioncitas.usuarios.dto.UsuarioResponseDto;
import com.conagrovet.gestioncitas.usuarios.entity.Usuario;
import com.conagrovet.gestioncitas.usuarios.repository.UsuariosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class MascotasServiceImpl implements MascotasService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Value("${imagen.directorio.mascota}")
    private String directorioImagenes;

    LocalDate fechaActual = LocalDate.now();
    Date fechaActualDate = java.sql.Date.valueOf(fechaActual);

    @Override
    public List<MascotaResponseDto> getListMascota() {
        List<Mascota> listaMascotas = mascotaRepository.findMascotas();
        List<MascotaResponseDto> listaMascotasDto = new ArrayList<>();
        MascotaResponseDto mascotaDto = new MascotaResponseDto();

        for (Mascota m : listaMascotas ){
            mascotaDto = this.mapMascotaDto(m,true);
            listaMascotasDto.add(mascotaDto);
        }
        return listaMascotasDto;
    }

    @Override
    public List<MascotaResponseDto> getListMascotaById(Integer id) {
        List<Mascota> listaMascotas = mascotaRepository.findMascotasById(id);
        List<MascotaResponseDto> listaMascotasDto = new ArrayList<>();
        MascotaResponseDto mascotaDto = new MascotaResponseDto();
        for (Mascota m : listaMascotas ){
            mascotaDto = this.mapMascotaDto(m,true);
            listaMascotasDto.add(mascotaDto);
        }
        return listaMascotasDto;
    }

    @Override
    public List<MascotaResponseDto> getListMascotaByDocumento(String num_doc) {
        Usuario usuario = usuariosRepository.findFirstByDocumento(num_doc);

        List<Mascota> listaMascotas = mascotaRepository.findMascotasById(usuario.getId());
        List<MascotaResponseDto> listaMascotasDto = new ArrayList<>();
        MascotaResponseDto mascotaDto = new MascotaResponseDto();
        for (Mascota m : listaMascotas ){
            mascotaDto = this.mapMascotaDto(m,true);
            listaMascotasDto.add(mascotaDto);
        }
        return listaMascotasDto;
    }

    @Override
    public Boolean saveMascota(String nombre, String usuario_id, String color, String tipo, String raza, Character sexo, Character esterilizado, Character estado, MultipartFile imagen, Date fecha_nacimiento, Double peso) {
        Usuario usuario = usuariosRepository.findFirstByDocumento(usuario_id);

        try {
            Mascota mascota = new Mascota();
            mascota.setNombre(nombre);
            mascota.setUsuario_id(usuario.getId());
            mascota.setColor(color);
            mascota.setTipo(tipo);
            mascota.setRaza(raza);
            mascota.setSexo(sexo);
            mascota.setEsterilizado(esterilizado);
            mascota.setEstado(estado);
            mascota.setFecha_nacimiento(fecha_nacimiento);
            mascota.setPeso(peso);
            mascota.setCreated_at(fechaActualDate);
            mascota.setCreated_user(1);

            if (!imagen.isEmpty()) {
                try {
                    String filename = imagen.getOriginalFilename();
                    int dotIndex = filename.lastIndexOf('.');
                    String extension = filename.substring(dotIndex + 1);
                    String nombreImagen = "mascota_" + usuario_id + "_" + System.currentTimeMillis() + "." + extension;

                    byte[] bytes = imagen.getBytes();
                    Path rutaCompleta = Paths.get(directorioImagenes + "//" + nombreImagen);
                    Files.write(rutaCompleta, bytes);
                    mascota.setImagen(nombreImagen);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {

                mascota.setImagen("incognito.jpg");

            }
            mascotaRepository.save(mascota);
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean updateMascota(String nombre, String num_doc, String color, String tipo, String raza, Character sexo, Character esterilizado, Character estado, MultipartFile imagen, Date fecha_nacimiento, Double peso, Integer id) {
      try {
        Usuario usuario = usuariosRepository.findFirstByDocumento(num_doc);
        Mascota mascota = mascotaRepository.findFirstById(id);
        mascota.setId(id);
        mascota.setNombre(nombre);
        mascota.setUsuario_id(usuario.getId());
        mascota.setColor(color);
        mascota.setTipo(tipo);
        mascota.setRaza(raza);
        mascota.setSexo(sexo);
        mascota.setEsterilizado(esterilizado);
        mascota.setEstado(estado);
        mascota.setFecha_nacimiento(fecha_nacimiento);
        mascota.setPeso(peso);
        if (!imagen.isEmpty()) {
            try {
                String filename = imagen.getOriginalFilename();
                int dotIndex = filename.lastIndexOf('.');
                String extension = filename.substring(dotIndex + 1);
                String nombreImagen = "mascota_" + num_doc + "_" + System.currentTimeMillis() + "." + extension;

                byte[] bytes = imagen.getBytes();
                Path rutaCompleta = Paths.get(directorioImagenes + "//" + nombreImagen);
                Files.write(rutaCompleta, bytes);
                mascota.setImagen(nombreImagen);

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        mascotaRepository.save(mascota);
          return true;
      }catch (Exception e){
          return false;
      }

    }

    @Override
    public MascotaResponseDto getMascota(Integer id) {
        MascotaResponseDto mascotaResponseDto = new MascotaResponseDto();
        Mascota mascota = mascotaRepository.findFirstById(id);
        mascotaResponseDto = mapMascotaDto(mascota,true);
        return mascotaResponseDto;
    }

    @Override
    public Boolean eliminarMascota(Integer id) {
        Mascota mascota = mascotaRepository.findFirstById(id);
        if (mascota!= null){
            mascota.setEstado('I');
            mascotaRepository.save(mascota);
            return true;
        }
        return false;
    }


    private MascotaResponseDto mapMascotaDto(Mascota mascota,boolean enviarUsuario) {
        MascotaResponseDto mascotaDto = new MascotaResponseDto();
        if (enviarUsuario){
            UsuarioResponseDto usuarioDto = new UsuarioResponseDto();
            Usuario usuario =  usuariosRepository.findById(mascota.getUsuario_id()).orElse(null);
            if (usuario != null){
               usuarioDto.setId(usuario.getId());
               usuarioDto.setNombre_completo(usuario.getNombre().concat(" ").concat(usuario.getApellido_paterno()).concat(" ").concat(usuario.getApellido_materno()));
               usuarioDto.setEmail(usuario.getEmail());
               usuarioDto.setNum_doc(usuario.getNum_doc());
               mascotaDto.setUsuario(usuarioDto);
            }
        }
        mascotaDto.setId(mascota.getId());
        mascotaDto.setNombre(mascota.getNombre());
        mascotaDto.setTipo(mascota.getTipo());
        mascotaDto.setRaza(mascota.getRaza());
        mascotaDto.setColor(mascota.getColor());
        mascotaDto.setPeso(mascota.getPeso());
        mascotaDto.setSexo(mascota.getSexo());
        mascotaDto.setImagen(mascota.getImagen());
        mascotaDto.setEsterilizado(mascota.getEsterilizado());
        mascotaDto.setEstado(mascota.getEstado());
        mascotaDto.setFecha_nacimiento(mascota.getFecha_nacimiento());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date fecha = mascota.getFecha_nacimiento();
        String fechaFormateada = dateFormat.format(fecha);
        mascotaDto.setFecha_nacimiento_string(fechaFormateada);
        return mascotaDto;
    }
}
