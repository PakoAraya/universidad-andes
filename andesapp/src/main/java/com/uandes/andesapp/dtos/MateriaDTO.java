package com.uandes.andesapp.dtos;

import com.uandes.andesapp.models.Alumno;
import com.uandes.andesapp.models.Materia;
import org.springframework.stereotype.Component;

@Component
public class MateriaDTO {
  private Long id;
  private String nombre;
  private Alumno alumno;

  public MateriaDTO() {}

  public MateriaDTO(Long id, String nombre, Alumno alumno) {
    this.id = id;
    this.nombre = nombre;
    this.alumno = alumno;
  }

  public MateriaDTO(Materia materia) {
    this.id = materia.getId();
    this.nombre = materia.getNombre();
    this.alumno = materia.getAlumno();
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
