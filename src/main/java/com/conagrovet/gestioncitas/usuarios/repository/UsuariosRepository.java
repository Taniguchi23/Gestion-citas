package com.conagrovet.gestioncitas.usuarios.repository;

import com.conagrovet.gestioncitas.usuarios.dto.UsuarioDto;
import com.conagrovet.gestioncitas.usuarios.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario,Long> {



}
