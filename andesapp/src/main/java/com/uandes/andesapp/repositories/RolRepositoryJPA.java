package com.uandes.andesapp.repositories;

import com.uandes.andesapp.models.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RolRepositoryJPA extends JpaRepository<Rol, Long> {
  Optional<Rol> findByNombre(String nombre);
}
