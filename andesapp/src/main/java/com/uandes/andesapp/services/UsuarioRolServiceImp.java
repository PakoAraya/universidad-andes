package com.uandes.andesapp.services;

import com.uandes.andesapp.dtos.UsuarioRolDTO;
import com.uandes.andesapp.interfaces.IUsuiarioRolService;
import com.uandes.andesapp.models.Usuario;
import com.uandes.andesapp.models.Rol;
import com.uandes.andesapp.models.UsuarioRol;
import com.uandes.andesapp.repositories.UsuarioRolRepositoryJPA;
import com.uandes.andesapp.repositories.UsuarioRepositoryJPA;
import com.uandes.andesapp.repositories.RolRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioRolServiceImp implements IUsuiarioRolService {

  @Autowired
  private UsuarioRolRepositoryJPA usuarioRolRepositoryJPA;

  @Autowired
  private UsuarioRepositoryJPA usuarioRepositoryJPA;

  @Autowired
  private RolRepositoryJPA rolRepositoryJPA;

  @Override
  public UsuarioRolDTO save(UsuarioRolDTO usuarioRolDTO) {
    Optional<Usuario> usuarioOptional = usuarioRepositoryJPA.findById(usuarioRolDTO.getUsuarioId());
    Optional<Rol> rolOptional = rolRepositoryJPA.findById(usuarioRolDTO.getRolId());

    if (usuarioOptional.isPresent() && rolOptional.isPresent()) {
      Usuario usuario = usuarioOptional.get();
      Rol rol = rolOptional.get();

      UsuarioRol usuarioRol = new UsuarioRol();
      usuarioRol.setUsuario(usuario);
      usuarioRol.setRol(rol);

      UsuarioRol savedUsuarioRol = usuarioRolRepositoryJPA.save(usuarioRol);

      return new UsuarioRolDTO(savedUsuarioRol);
    } else {
      throw new RuntimeException("Usuario o Rol no encontrado");
    }
  }

  @Override
  public UsuarioRolDTO findById(Long id) {
    // Buscar un UsuarioRol por su ID
    Optional<UsuarioRol> usuarioRol = usuarioRolRepositoryJPA.findById(id);

    // Si existe, convertirlo a DTO; de lo contrario, devolver null
    return usuarioRol.map(UsuarioRolDTO::new).orElse(null);
  }

  @Override
  public List<UsuarioRolDTO> findAll() {
    List<UsuarioRol> usuarioRoles = usuarioRolRepositoryJPA.findAll();

    return usuarioRoles.stream()
            .map(UsuarioRolDTO::new)
            .collect(Collectors.toList());
  }

  @Override
  public void deleteById(Long id) {
    usuarioRolRepositoryJPA.deleteById(id);
  }

  @Override
  public UsuarioRolDTO update(Long id, UsuarioRolDTO usuarioRolDTO) {
    Optional<UsuarioRol> usuarioRolOptional = usuarioRolRepositoryJPA.findById(id);

    if (usuarioRolOptional.isPresent()) {
      UsuarioRol usuarioRol = usuarioRolOptional.get();

      // Buscar el usuario y el rol por sus IDs
      Optional<Usuario> usuarioOptional = usuarioRepositoryJPA.findById(usuarioRolDTO.getUsuarioId());
      Optional<Rol> rolOptional = rolRepositoryJPA.findById(usuarioRolDTO.getRolId());

      if (usuarioOptional.isPresent() && rolOptional.isPresent()) {
        Usuario usuario = usuarioOptional.get();
        Rol rol = rolOptional.get();

        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);

        UsuarioRol updatedUsuarioRol = usuarioRolRepositoryJPA.save(usuarioRol);

        return new UsuarioRolDTO(updatedUsuarioRol);
      } else {
        throw new RuntimeException("Usuario o Rol no encontrado");
      }
    } else {
      throw new RuntimeException("UsuarioRol no encontrado con ID: " + id);
    }
  }

  @Override
  public List<UsuarioRolDTO> findByUsuarioId(Long usuarioId) {
    List<UsuarioRol> usuarioRoles = usuarioRolRepositoryJPA.findByUsuarioId(usuarioId);

    return usuarioRoles.stream()
            .map(UsuarioRolDTO::new)
            .collect(Collectors.toList());
  }

  @Override
  public List<UsuarioRolDTO> findByRolId(Long rolId) {
    List<UsuarioRol> usuarioRoles = usuarioRolRepositoryJPA.findByRolId(rolId);

    return usuarioRoles.stream()
            .map(UsuarioRolDTO::new)
            .collect(Collectors.toList());
  }
}