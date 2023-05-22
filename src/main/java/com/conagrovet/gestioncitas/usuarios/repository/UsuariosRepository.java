package com.conagrovet.gestioncitas.usuarios.repository;

import com.conagrovet.gestioncitas.usuarios.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario,Integer> {
    @Query("SELECT u FROM Usuario u WHERE u.estado = 'A' and u.rol = 'V' ")
    List<Usuario> findVeterinariosActivos();
    @Query("SELECT u FROM Usuario u WHERE u.estado = 'A' and u.rol = 'C' ")
    List<Usuario> findClientesActivos();

    @Query("SELECT u FROM Usuario u WHERE u.estado = 'A' and u.rol = 'A' ")
    List<Usuario> findAdministradoresActivos();

}
