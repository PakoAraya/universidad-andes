package com.uandes.andesapp.repositories;

import com.uandes.andesapp.models.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MateriaRepositoryJPA extends JpaRepository<Materia, Long> {
  List<Materia> findByAlumnoId(Long alumnoId);
}
