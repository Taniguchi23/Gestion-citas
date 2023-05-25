package com.conagrovet.gestioncitas.global.helpers;

import com.conagrovet.gestioncitas.usuarios.dto.UsuarioDto;
import com.conagrovet.gestioncitas.usuarios.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.service.invoker.HttpClientAdapter;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpClient;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Util {
    private String apiUrl =  "https://conagrovet.utp-sistemas.com/service-conagrovet/login";
    private String directorioImagen = "";

    public static String guardarArchivo(MultipartFile multiPart, String ruta){
        String nombreOriginal= multiPart.getOriginalFilename();
        try {
            File imageFile = new File(ruta + nombreOriginal);
            multiPart.transferTo(imageFile);
            return nombreOriginal;
        } catch (IOException e) {
            return null;
        }
    }

    public static  UsuarioDto mapUsuarioToUsuarioDto(Usuario usuario) {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuario.getId());
        usuarioDto.setNombre(usuario.getNombre());
        usuarioDto.setApellido_paterno(usuario.getApellido_paterno());
        usuarioDto.setApellido_materno(usuario.getApellido_materno());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setTipo_doc(usuario.getTipo_doc());
        usuarioDto.setNum_doc(usuario.getNum_doc());
        usuarioDto.setSexo(usuario.getSexo());
        usuarioDto.setTelefono(usuario.getTelefono());
        usuarioDto.setRol(usuario.getRol());
        usuarioDto.setEstado(usuario.getEstado());
        usuarioDto.setImagen(usuario.getImagen());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = usuario.getFecha_nacimiento();
        String fechaFormateada = dateFormat.format(fecha);
        usuarioDto.setFecha_nacimiento(usuario.getFecha_nacimiento());
        usuarioDto.setFecha_nacimiento_string(fechaFormateada);
        return usuarioDto;

    }
}
