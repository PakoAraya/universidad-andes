package com.uandes.andesapp.services;

import com.uandes.andesapp.dtos.UsuarioDTO;
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

  // Inyectar el servicio de roles (esto falta implementar)
  // @Autowired
  // private RolService rolService;

  @Override
  public UsuarioDTO save(UsuarioDTO usuarioDTO) {
    Usuario usuario = new Usuario();
    usuario.setNombre(usuarioDTO.getNombre());
    usuario.setUsername(usuarioDTO.getUsername());
    usuario.setEmail(usuarioDTO.getEmail());
    usuario.setPassword(usuarioDTO.getPassword());

     if (usuarioDTO.getRoles() != null) {
         List<Rol> roles = rolService.findByNombres(usuarioDTO.getRoles());
         usuario.setRoles(roles);
     }

    Usuario savedUsuario = usuarioRepositoryJPA.save(usuario);

    return new UsuarioDTO(savedUsuario);
  }

  @Override
  public UsuarioDTO findById(Long id) {
    Optional<Usuario> usuario = usuarioRepositoryJPA.findById(id);

    // Si el usuario existe, convertirlo a DTO; de lo contrario, devolver null
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

    // Si el usuario existe, actualizar sus datos
    if (usuarioOptional.isPresent()) {
      Usuario usuario = usuarioOptional.get();
      usuario.setNombre(usuarioDTO.getNombre());
      usuario.setUsername(usuarioDTO.getUsername());
      usuario.setEmail(usuarioDTO.getEmail());
      usuario.setPassword(usuarioDTO.getPassword());


       if (usuarioDTO.getRoles() != null) {
           List<Rol> roles = rolService.findByNombres(usuarioDTO.getRoles());
           usuario.setRoles(roles);
       }

      usuarioRepositoryJPA.save(usuario);
    }
  }
}