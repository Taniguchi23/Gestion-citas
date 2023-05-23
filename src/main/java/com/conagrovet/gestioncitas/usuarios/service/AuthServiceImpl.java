package com.conagrovet.gestioncitas.usuarios.service;

import com.conagrovet.gestioncitas.usuarios.dto.ResponseLoginDto;
import com.conagrovet.gestioncitas.usuarios.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UsuariosRepository repo;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Override
    public ResponseLoginDto login(String email, String password) {
        var usuario = repo.findUserByEmail(email);
        ResponseLoginDto responseLogin = new ResponseLoginDto();
        if (usuario !=null){
           boolean response =  encoder.matches(password, usuario.get(0).getPassword());
           if (response){
               if (usuario.get(0).getEstado().equals("I")){
                   responseLogin.setEstado("error");
                   responseLogin.setMensaje("Usuario inactivo");
               }else {
                   responseLogin.setEstado("ok");
                   responseLogin.setMensaje("Usuario valido");
                   responseLogin.setUsuario(usuario.get(0));
               }
           }else {
               responseLogin.setEstado("error");
               responseLogin.setMensaje("Credenciales incorrectas");
           }
        }else {
            responseLogin.setEstado("error");
            responseLogin.setMensaje("Usuario no encontrado");
        }

        return responseLogin;
    }
}
