package com.conagrovet.gestioncitas.usuarios.service;


import com.conagrovet.gestioncitas.global.helpers.Util;
import com.conagrovet.gestioncitas.usuarios.dto.UsuarioDto;
import com.conagrovet.gestioncitas.usuarios.entity.Usuario;
import com.conagrovet.gestioncitas.usuarios.repository.UsuariosRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UsuariosServiceImpl implements UsuariosService {

    @Autowired
    private UsuariosRepository repo;


   // private PasswordEncoder passwordEncoder;

    @Value("${imagen.directorio.avatar}")
    private String directorioImagenes;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    LocalDate fechaActual = LocalDate.now();
    Date fechaActualDate = java.sql.Date.valueOf(fechaActual);
    @Override
    public List<UsuarioDto> listaVeterinariosActivos() {
        List<Usuario> usuarios = repo.findVeterinariosActivos();
        return usuarios.stream()
                .map(this::mapUsuarioToUsuarioDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UsuarioDto> listaClientesActivos() {
        List<Usuario> usuarios = repo.findClientesActivos();
        return usuarios.stream()
                .map(this::mapUsuarioToUsuarioDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UsuarioDto> listaAdministradoresActivos() {
        List<Usuario> usuarios = repo.findAdministradoresActivos();
        return usuarios.stream()
                .map(this::mapUsuarioToUsuarioDto)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean guardarUsuario(String nombre, String apellido_paterno, String apellido_materno, Integer tipo_doc, String num_doc, String email, String password, String telefono, Character sexo, Date fecha_nacimiento, MultipartFile imagen , Character estado, Character rol) {

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido_paterno(apellido_paterno);
        usuario.setApellido_materno(apellido_materno);
        usuario.setTipo_doc(tipo_doc);
        usuario.setNum_doc(num_doc);
        usuario.setEmail(email);
        usuario.setPassword(encoder.encode(password));
        usuario.setTelefono(telefono);
        usuario.setFecha_nacimiento(fecha_nacimiento);
        usuario.setEstado(estado);
        usuario.setRol(rol);
        usuario.setSexo(sexo);
        usuario.setCreated_at(fechaActualDate);
        usuario.setCreated_user(1);
        if (!imagen.isEmpty()) {

            try {
                String filename = imagen.getOriginalFilename();
                int dotIndex = filename.lastIndexOf('.');
                String extension = filename.substring(dotIndex + 1);
                String nombreImagen = "avatar_"+num_doc+"_" + System.currentTimeMillis()+"."+extension;

                byte[] bytes = imagen.getBytes();
                Path rutaCompleta = Paths.get(directorioImagenes + "//"+nombreImagen);
                Files.write(rutaCompleta, bytes);
                                usuario.setImagen(nombreImagen);

            }catch (IOException e){
                e.printStackTrace();
            }
        }else {
            if (Character.toString('M').equals(sexo)){
                usuario.setImagen("mujer.jpg");
            }else {
                usuario.setImagen("hombre.jpg");
            }
        }
        repo.save(usuario);
        return  true;
    }

    @Override
    public Boolean actualizarUsuario(String nombre, String apellido_paterno, String apellido_materno, Integer tipo_doc, String num_doc, String email, String password, String telefono, Character sexo, Date fecha_nacimiento, MultipartFile imagen , Character estado, Character rol, Integer id) {

        Usuario usuario = repo.findById(id).orElse(null);
        usuario.setNombre(nombre);
        usuario.setApellido_paterno(apellido_paterno);
        usuario.setApellido_materno(apellido_materno);
        usuario.setTipo_doc(tipo_doc);
        usuario.setNum_doc(num_doc);
        usuario.setEmail(email);
        usuario.setPassword(encoder.encode(password));
        usuario.setTelefono(telefono);
        usuario.setFecha_nacimiento(fecha_nacimiento);
        usuario.setEstado(estado);
        usuario.setRol(rol);
        usuario.setSexo(sexo);
        usuario.setCreated_at(fechaActualDate);
        usuario.setCreated_user(1);
        if (!imagen.isEmpty()) {

            try {
                String filename = imagen.getOriginalFilename();
                int dotIndex = filename.lastIndexOf('.');
                String extension = filename.substring(dotIndex + 1);
                String nombreImagen = "avatar_"+num_doc+"_" + System.currentTimeMillis()+"."+extension;

                byte[] bytes = imagen.getBytes();
                Path rutaCompleta = Paths.get(directorioImagenes + "//"+nombreImagen);
                Files.write(rutaCompleta, bytes);
                usuario.setImagen(nombreImagen);

            }catch (IOException e){
                e.printStackTrace();
                return false;
            }
        }

        repo.save(usuario);
        return  true;
    }
    @Override
    public Boolean eliminarUsuario(Integer id) {
        try {
            Optional<Usuario> usuarioOptional = repo.findById(id);
            if (usuarioOptional.isPresent()) {
                Usuario usuario = usuarioOptional.get();
                usuario.setEstado('I');
                repo.save(usuario);
            } else {
                return  false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public List<String> verificarUsuario(String email, String num_doc, String accion, Integer id) {
        List<String> listaMensaje = new ArrayList<>();
        if (accion.equals("S")){
        Pageable pageable = PageRequest.of(0, 1);
        List<Usuario>  listaUsuario = repo.existUsuario(email,num_doc);
        if (listaUsuario.size() > 0){
            if (listaUsuario.get(0).getEmail().equals(email)){
                String mensaje = "email";
                listaMensaje.add(mensaje);
            }
            if (listaUsuario.get(0).getNum_doc().equals(num_doc)){
                String mensaje = "documento";
                listaMensaje.add(mensaje);
            }
        }
        }else {
            List<Usuario> listaUsuario = repo.existUsuario(email,num_doc);
            boolean flag = false;
            for (Usuario u: listaUsuario){
                if (u !=null && u.getId() != id) {
                    if (u.getEmail().equals(email)){
                        String mensaje = "email";
                        listaMensaje.add(mensaje);
                    }
                    if (u.getNum_doc().equals(num_doc)){
                        String mensaje = "documento";
                        listaMensaje.add(mensaje);
                    }
                }
            }
        }
        return listaMensaje;
    }

    @Override

    public Boolean findByDocumento(String documento) {
        Usuario usuario = repo.findFirstByDocumento(documento);
        return usuario!= null;
    }

    @Override
    public Usuario findUsuarioById(Integer id) {
        return  repo.findById(id).orElse(null);

    }

    private UsuarioDto mapUsuarioToUsuarioDto(Usuario usuario) {
        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(usuario.getId());
        usuarioDto.setNombre(usuario.getNombre());
        usuarioDto.setApellido_paterno(usuario.getApellido_paterno());
        usuarioDto.setApellido_materno(usuario.getApellido_materno());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setTipo_doc(usuario.getTipo_doc());
        usuarioDto.setNum_doc(usuario.getNum_doc());
        usuarioDto.setRol(usuario.getRol());
        usuarioDto.setEstado(usuario.getEstado());
        usuarioDto.setImagen(usuario.getImagen());
        usuarioDto.setFecha_nacimiento(usuario.getFecha_nacimiento());
        return usuarioDto;
    }
}
