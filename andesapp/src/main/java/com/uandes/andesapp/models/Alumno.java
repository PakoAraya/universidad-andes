package com.uandes.andesapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "alumno")
public class Alumno {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "rut", nullable = false)
  @NotNull(message = "El campo rut no puede ser nulo")
  private String rut;

  @Column(name = "nombre", nullable = false)
  @NotNull(message = "El campo nombre no puede ser nulo")
  private String nombre;

  @Column(name = "direccion", nullable = false)
  private String direccion;

  @OneToMany(mappedBy = "alumno", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Materia> materia;

  public Alumno() {
  }

  public Alumno(Long id, String rut, String nombre, String direccion, List<Materia> materia) {
    this.id = id;
    this.rut = rut;
    this.nombre = nombre;
    this.direccion = direccion;
    this.materia = materia;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRut() {
    return rut;
  }

  public void setRut(String rut) {
    this.rut = rut;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public List<Materia> getMateria() {
    return materia;
  }

  public void setMateria(List<Materia> materia) {
    this.materia = materia;
  }
}
