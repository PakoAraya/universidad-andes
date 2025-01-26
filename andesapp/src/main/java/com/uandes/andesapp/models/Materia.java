package com.uandes.andesapp.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "materia")
public class Materia {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "nombre", nullable = false)
  @NotNull(message = "El campo nombre de materia no puede ser nulo")
  private String nombre;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "alumno_id", nullable = false)
  private Alumno alumno;

  public Materia() {
  }

  public Materia(Long id, String nombre, Alumno alumno) {
    this.id = id;
    this.nombre = nombre;
    this.alumno = alumno;
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

  public Alumno getAlumno() {
    return alumno;
  }

  public void setAlumno(Alumno alumno) {
    this.alumno = alumno;
  }
}
