package com.uandes.andesapp.dtos;

import com.uandes.andesapp.models.Usuario;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDTO {
  private Long id;
  private String nombre;
  private String username;
  private String email;
  private String password;
  private List<String> roles;

  public UsuarioDTO() {}

  public UsuarioDTO(Long id, String nombre, String username, String email, String password, List<String> roles) {
    this.id = id;
    this.nombre = nombre;
    this.username = username;
    this.email = email;
    this.password = password;
    this.roles = roles;
  }

  public UsuarioDTO(Usuario usuario) {
    this.id = usuario.getId();
    this.nombre = usuario.getNombre();
    this.username = usuario.getUsername();
    this.email = usuario.getEmail();
    this.password = usuario.getPassword();
    this.roles = usuario.getUsuarioRoles().stream()
            .map(usuarioRol -> usuarioRol.getRol().getNombre())  // Obtener el nombre del rol
            .collect(Collectors.toList());  // Convertir a lista
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

  public String getUsername() {
    return username;
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public List<String> getRoles() {
    return roles;
  }

  public void setRoles(List<String> roles) {
    this.roles = roles;
  }
}