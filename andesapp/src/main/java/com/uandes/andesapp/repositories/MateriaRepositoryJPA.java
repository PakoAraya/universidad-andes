package com.uandes.andesapp.repositories;

import com.uandes.andesapp.models.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MateriaRepositoryJPA extends JpaRepository<Materia, Long> {

}
