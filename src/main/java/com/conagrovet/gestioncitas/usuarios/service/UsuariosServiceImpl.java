package com.conagrovet.gestioncitas.usuarios.service;

import com.conagrovet.gestioncitas.usuarios.dto.UsuarioDto;
import com.conagrovet.gestioncitas.usuarios.entity.Usuario;
import com.conagrovet.gestioncitas.usuarios.repository.UsuariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuariosServiceImpl {
    @Autowired
    private UsuariosRepository repo;


}
