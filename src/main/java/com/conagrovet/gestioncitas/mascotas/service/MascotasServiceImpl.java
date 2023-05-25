package com.conagrovet.gestioncitas.mascotas.service;

import com.conagrovet.gestioncitas.mascotas.dto.MascotaResponseDto;
import com.conagrovet.gestioncitas.mascotas.entity.Mascota;
import com.conagrovet.gestioncitas.mascotas.repository.MascotaRepository;
import com.conagrovet.gestioncitas.usuarios.dto.UsuarioResponseDto;
import com.conagrovet.gestioncitas.usuarios.entity.Usuario;
import com.conagrovet.gestioncitas.usuarios.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MascotasServiceImpl implements MascotasService {

    @Autowired
    private MascotaRepository mascotaRepository;

    @Autowired
    private UsuariosRepository usuariosRepository;

    @Value("${imagen.directorio.mascota}")
    private String directorioImagenes;

    @Override
    public List<MascotaResponseDto> getListMascota() {
        List<Mascota> listaMascotas = mascotaRepository.findMascotas();
        List<MascotaResponseDto> listaMascotasDto = new ArrayList<>();
        MascotaResponseDto mascotaDto = new MascotaResponseDto();

            for (Mascota m : listaMascotas ){
            mascotaDto = this.mapMascotaDto(m,false);
            listaMascotasDto.add(mascotaDto);
        }
        return listaMascotasDto;
    }

    @Override
    public Boolean saveMascota(String nombre, Integer usuario_id, String color, String tipo, String raza, Character sexo, Character esterilizado, Character estado, MultipartFile imagen, Date fecha_nacimiento, Double peso) {
        try {
            Mascota mascota = new Mascota();
            mascota.setNombre(nombre);
            mascota.setUsuario_id(usuario_id);
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
    public MascotaResponseDto getMascota(Integer id) {
        MascotaResponseDto mascotaResponseDto = new MascotaResponseDto();
        Mascota mascota = mascotaRepository.findFirstById(id);
        mascotaResponseDto = mapMascotaDto(mascota,true);
        return mascotaResponseDto;
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
               mascotaDto.setUsuario(usuarioDto);
            }
        }

        mascotaDto.setNombre(mascota.getNombre());
        mascotaDto.setTipo(mascota.getTipo());
        mascotaDto.setRaza(mascota.getRaza());
        mascotaDto.setColor(mascota.getColor());
        mascotaDto.setPeso(mascota.getPeso());
        mascotaDto.setSexo(mascota.getSexo());
        mascotaDto.setImagen(mascota.getImagen());
        mascotaDto.setEsterilizado(mascota.getEsterilizado());
        mascotaDto.setEstado(mascota.getEstado());
        return mascotaDto;
    }
}
