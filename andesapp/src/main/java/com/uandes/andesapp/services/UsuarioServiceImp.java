package com.uandes.andesapp.services;

import com.uandes.andesapp.dtos.UsuarioDTO;
import com.uandes.andesapp.dtos.UsuarioRolDTO;
import com.uandes.andesapp.interfaces.IUsuarioService;
import com.uandes.andesapp.models.Rol;
import com.uandes.andesapp.models.Usuario;
import com.uandes.andesapp.repositories.UsuarioRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImp implements IUsuarioService {

  @Autowired
  private UsuarioRepositoryJPA usuarioRepositoryJPA;

  @Autowired
  private RolServiceImp rolServiceIMP;

  @Autowired
  private UsuarioRolServiceImp usuarioRolServiceIMP;

  @Override
  public UsuarioDTO save(UsuarioDTO usuarioDTO) {
    Usuario usuario = new Usuario();
    usuario.setNombre(usuarioDTO.getNombre());
    usuario.setUsername(usuarioDTO.getUsername());
    usuario.setEmail(usuarioDTO.getEmail());
    usuario.setPassword(usuarioDTO.getPassword());

    // Guardar el usuario en la base de datos
    Usuario savedUsuario = usuarioRepositoryJPA.save(usuario);

    // Asignar roles al usuario a través de UsuarioRol
    if (usuarioDTO.getRoles() != null) {
      for (String rolNombre : usuarioDTO.getRoles()) {
        Rol rol = rolServiceIMP.findByNombre(rolNombre);
        if (rol != null) {
          usuarioRolServiceIMP.save(new UsuarioRolDTO(null, savedUsuario.getId(), rol.getId()));
        }
      }
    }

    return new UsuarioDTO(savedUsuario);
  }

  @Override
  public UsuarioDTO findById(Long id) {
    Optional<Usuario> usuario = usuarioRepositoryJPA.findById(id);
    return usuario.map(UsuarioDTO::new).orElse(null);
  }

  @Override
  public List<UsuarioDTO> findAll() {
    List<Usuario> usuarios = usuarioRepositoryJPA.findAll();
    return usuarios.stream()
            .map(UsuarioDTO::new)
            .collect(Collectors.toList());
  }

  @Override
  public void delete(Long id) {
    usuarioRepositoryJPA.deleteById(id);
  }

  @Override
  public void update(UsuarioDTO usuarioDTO) {
    Optional<Usuario> usuarioOptional = usuarioRepositoryJPA.findById(usuarioDTO.getId());
    if (usuarioOptional.isPresent()) {
      Usuario usuario = usuarioOptional.get();
      usuario.setNombre(usuarioDTO.getNombre());
      usuario.setUsername(usuarioDTO.getUsername());
      usuario.setEmail(usuarioDTO.getEmail());
      usuario.setPassword(usuarioDTO.getPassword());

      // Actualizar roles del usuario a través de UsuarioRol
      if (usuarioDTO.getRoles() != null) {
        for (String rolNombre : usuarioDTO.getRoles()) {
          Rol rol = rolServiceIMP.findByNombre(rolNombre);
          if (rol != null) {
            usuarioRolServiceIMP.save(new UsuarioRolDTO(null, usuario.getId(), rol.getId()));
          }
        }
      }

      usuarioRepositoryJPA.save(usuario);
    }
  }
}