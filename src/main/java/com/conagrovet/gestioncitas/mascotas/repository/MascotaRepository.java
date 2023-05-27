package com.conagrovet.gestioncitas.mascotas.repository;

import com.conagrovet.gestioncitas.mascotas.entity.Mascota;
import com.conagrovet.gestioncitas.usuarios.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota,Integer> {
    @Query("SELECT m  FROM Mascota m  where  m.estado = 'A' ORDER BY m.id DESC ")
    List<Mascota> findMascotas();
    @Query("SELECT m  FROM Mascota m  where  m.estado = 'A' and  m.usuario_id= :id ORDER BY m.id DESC ")
    List<Mascota> findMascotasById(@Param("id") Integer id);


    @Query("SELECT m FROM Mascota m WHERE m.id = :id ")
    Mascota findFirstById(@Param("id") Integer id);
}
