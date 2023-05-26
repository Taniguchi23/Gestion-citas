package com.conagrovet.gestioncitas.citas.repository;

import com.conagrovet.gestioncitas.citas.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitasRepository extends JpaRepository<Cita,Integer> {

}
