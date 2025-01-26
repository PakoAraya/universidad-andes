package com.uandes.andesapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "usuario")
public class Usuario implements UserDetails {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nombre", nullable = false)
  @NotNull(message = "El campo nombre no puede ser nulo")
  private String nombre;

  @Column(name = "username", nullable = false, unique = true)
  @NotNull(message = "El campo username no puede ser nulo")
  private String username;

  @Column(name = "email", nullable = false, unique = true)
  @NotNull(message = "El campo email no puede ser nulo")
  private String email;

  @Column(name = "password", nullable = false)
  @NotNull(message = "El campo password no puede ser nulo")
  private String password;

  @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<UsuarioRol> usuarioRoles;

  public Usuario() {}

  public Usuario(Long id, String nombre, String username, String email, String password, List<UsuarioRol> usuarioRoles) {
    this.id = id;
    this.nombre = nombre;
    this.username = username;
    this.email = email;
    this.password = password;
    this.usuarioRoles = usuarioRoles;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return usuarioRoles.stream()
            .map(UsuarioRol::getRol)
            .collect(Collectors.toList());
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
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

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<UsuarioRol> getUsuarioRoles() {
    return usuarioRoles;
  }

  public void setUsuarioRoles(List<UsuarioRol> usuarioRoles) {
    this.usuarioRoles = usuarioRoles;
  }
}