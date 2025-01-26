package com.uandes.andesapp.dtos;

import com.uandes.andesapp.models.Rol;
import java.util.List;
import java.util.stream.Collectors;

public class RolDTO {
  private Long id;
  private String nombre;
  private List<Long> usuarioIds;

  public RolDTO() {}

  public RolDTO(Rol rol) {
    this.id = rol.getId();
    this.nombre = rol.getNombre();
    this.usuarioIds = rol.getUsuarioRoles().stream()
            .map(usuarioRol -> usuarioRol.getUsuario().getId())
            .collect(Collectors.toList());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public List<Long> getUsuarioIds() {
    return usuarioIds;
  }

  public void setUsuarioIds(List<Long> usuarioIds) {
    this.usuarioIds = usuarioIds;
  }
}