package com.uandes.andesapp.repositories;

import com.uandes.andesapp.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepositoryJPA extends JpaRepository<Usuario, Long> {
  Optional<Usuario> findByUsername(String username);
  Optional<Usuario> findByEmail(String email);
}
