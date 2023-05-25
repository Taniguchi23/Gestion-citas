package com.conagrovet.gestioncitas.usuarios.service;


import com.conagrovet.gestioncitas.usuarios.dto.UsuarioDto;
import com.conagrovet.gestioncitas.usuarios.entity.Usuario;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


import java.util.Date;
import java.util.List;

public interface UsuariosService {
    List<UsuarioDto>  listaVeterinariosActivos();
    List<UsuarioDto>  listaClientesActivos();
    List<UsuarioDto>  listaAdministradoresActivos();

    Boolean guardarUsuario(String nombre, String apellido_paterno, String apellido_materno, Integer tipo_doc, String num_doc, String email, String password,
             String telefono, Character sexo, Date fecha, MultipartFile imagen, Character estado, Character rol);

    Boolean actualizarUsuario(String nombre, String apellido_paterno, String apellido_materno, Integer tipo_doc, String num_doc, String email, String password,
                           String telefono, Character sexo, Date fecha, MultipartFile imagen, Character estado, Character rol, Integer id);

    Boolean eliminarUsuario(Integer id);
    List<String> verificarUsuario(String email, String num_doc, String accion, Integer id);

    Boolean findByDocumento(String documento);

    Usuario findUsuarioById(Integer id);

}
