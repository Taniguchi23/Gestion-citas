package com.conagrovet.gestioncitas.usuarios.repository;

import com.conagrovet.gestioncitas.usuarios.entity.Usuario;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuariosRepository extends JpaRepository<Usuario,Integer> {
    @Query("SELECT u FROM Usuario u ORDER BY u.id DESC ")
    List<Usuario> findVeterinariosActivos();
    @Query("SELECT u FROM Usuario u WHERE u.estado = 'A' and u.rol = 'C' ORDER BY u.id DESC ")
    List<Usuario> findClientesActivos();

    @Query("SELECT u FROM Usuario u WHERE u.estado = 'A' and u.rol = 'A' ORDER BY u.id DESC ")
    List<Usuario> findAdministradoresActivos();

    @Query("SELECT u FROM Usuario u WHERE u.email = :email or u.num_doc = :num_doc ")
    List<Usuario>  existUsuario(@Param("email") String email, @Param("num_doc") String num_doc);
    @Query("SELECT u FROM Usuario u WHERE u.email = :email ")
    List<Usuario>  findUserByEmail(@Param("email") String email);

    @Query("SELECT u FROM Usuario u WHERE u.num_doc = :documento ")
   Usuario  findFirstByDocumento(@Param("documento") String documento);
}
