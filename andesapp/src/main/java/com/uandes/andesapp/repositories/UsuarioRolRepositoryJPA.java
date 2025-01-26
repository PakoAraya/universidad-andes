package com.uandes.andesapp.repositories;

import com.uandes.andesapp.models.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsuarioRolRepositoryJPA extends JpaRepository<UsuarioRol, Long> {
  List<UsuarioRol> findByUsuarioId(Long usuarioId);
  List<UsuarioRol> findByRolId(Long rolId);
}
