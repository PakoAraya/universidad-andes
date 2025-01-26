package com.uandes.andesapp.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
@Table(name = "rol")
public class Rol implements GrantedAuthority {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nombre", nullable = false)
  @NotNull(message = "El campo nombre no puede ser nulo")
  private String nombre;

  @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<UsuarioRol> usuarioRoles;

  // Implementación de GrantedAuthority
  @Override
  public String getAuthority() {
    return nombre;
  }

  public Rol() {}

  public Rol(Long id, String nombre) {
    this.id = id;
    this.nombre = nombre;
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

  public List<UsuarioRol> getUsuarioRoles() {
    return usuarioRoles;
  }

  public void setUsuarioRoles(List<UsuarioRol> usuarioRoles) {
    this.usuarioRoles = usuarioRoles;
  }
}