package com.conagrovet.gestioncitas.citas.repository;

import com.conagrovet.gestioncitas.citas.entity.Detalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetallesRepository extends JpaRepository<Detalle,Integer> {
}
