package com.uandes.andesapp.repositories;

import com.uandes.andesapp.models.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlumnoRepositoryJPA extends JpaRepository<Alumno, Long> {

}
