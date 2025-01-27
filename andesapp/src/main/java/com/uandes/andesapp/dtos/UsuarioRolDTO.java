package com.uandes.andesapp.dtos;

import com.uandes.andesapp.models.UsuarioRol;

public class UsuarioRolDTO {
  private Long id;
  private Long usuarioId;
  private Long rolId;

  public UsuarioRolDTO() {}

  //Constructor que recibe los IDs de Usuario y Rol
  //necesarios para crear una nueva instancia de UsuarioRolDTO y UsuarioServiceImp
  public UsuarioRolDTO(Long id, Long usuarioId, Long rolId) {
    this.id = id;
    this.usuarioId = usuarioId;
    this.rolId = rolId;
  }

  public UsuarioRolDTO(UsuarioRol usuarioRol) {
    this.id = usuarioRol.getId();
    this.usuarioId = usuarioRol.getUsuario().getId();
    this.rolId = usuarioRol.getRol().getId();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getUsuarioId() {
    return usuarioId;
  }

  public void setUsuarioId(Long usuarioId) {
    this.usuarioId = usuarioId;
  }

  public Long getRolId() {
    return rolId;
  }

  public void setRolId(Long rolId) {
    this.rolId = rolId;
  }
}