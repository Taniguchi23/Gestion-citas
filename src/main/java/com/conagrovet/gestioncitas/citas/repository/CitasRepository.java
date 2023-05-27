package com.conagrovet.gestioncitas.citas.repository;

import com.conagrovet.gestioncitas.citas.entity.Cita;
import jakarta.persistence.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CitasRepository extends JpaRepository<Cita,Integer> {
    @Query("SELECT c FROM Cita c WHERE c.id = :id")
    Cita findFirstById(@Param("id") Integer id);

    @Query("SELECT c FROM Cita c WHERE c.estado = 'P' ORDER BY c.fecha_cita DESC")
    List<Cita> findPendienteCitasOrderByFechaCitaDesc();
    @Query("SELECT c FROM Cita c WHERE c.estado != 'P' ORDER BY c.fecha_cita DESC")
    List<Cita> findNoPendienteCitasOrderByFechaCitaDesc();

    @Query("SELECT c FROM Cita c WHERE c.estado = 'P' and c.mascota_id = :id ORDER BY c.fecha_cita DESC")
    List<Cita> findPendienteCitasOrderByFechaCitaDescById(@Param("id") Integer id);
    @Query("SELECT c FROM Cita c WHERE c.estado != 'P'  and c.mascota_id = :id ORDER BY c.fecha_cita DESC")
    List<Cita> findNoPendienteCitasOrderByFechaCitaDescById(@Param("id") Integer id);
}
