package com.uandes.andesapp.services;

import com.uandes.andesapp.dtos.RolDTO;
import com.uandes.andesapp.interfaces.IRolService;
import com.uandes.andesapp.models.Rol;
import com.uandes.andesapp.repositories.RolRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RolServiceImp implements IRolService {

  @Autowired
  private RolRepositoryJPA rolRepositoryJPA;

  @Override
  public RolDTO save(RolDTO rolDTO) {
    Rol rol = new Rol();
    rol.setNombre(rolDTO.getNombre());

    // Guardar el rol en la base de datos
    Rol savedRol = rolRepositoryJPA.save(rol);
    return new RolDTO(savedRol);
  }

  @Override
  public RolDTO findById(Long id) {
    Optional<Rol> rol = rolRepositoryJPA.findById(id);

    // Si el rol existe, convertirlo a DTO; de lo contrario, devolver null
    return rol.map(RolDTO::new).orElse(null);
  }

  @Override
  public List<RolDTO> findAll() {
    List<Rol> roles = rolRepositoryJPA.findAll();

    return roles.stream()
            .map(RolDTO::new)
            .collect(Collectors.toList());
  }

  @Override
  public void delete(Long id) {
    rolRepositoryJPA.deleteById(id);
  }

  @Override
  public RolDTO update(RolDTO rolDTO) {
    Optional<Rol> rolOptional = rolRepositoryJPA.findById(rolDTO.getId());

    // Si el rol existe, actualizar sus datos
    if (rolOptional.isPresent()) {
      Rol rol = rolOptional.get();
      rol.setNombre(rolDTO.getNombre());

      // Guardar el rol actualizado en la base de datos
      Rol updatedRol = rolRepositoryJPA.save(rol);
      return new RolDTO(updatedRol);
    }
    return null;
  }

  @Override
  public Rol findByNombre(String nombre) {
    // Buscar un rol por su nombre
    return rolRepositoryJPA.findByNombre(nombre).orElse(null);
  }
}