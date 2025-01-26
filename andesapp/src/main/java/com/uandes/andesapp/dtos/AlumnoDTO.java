package com.uandes.andesapp.dtos;

import com.uandes.andesapp.models.Alumno;
import com.uandes.andesapp.models.Materia;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AlumnoDTO {

  private Long id;
  private String rut;
  private String nombre;
  private String direccion;
  private List<Materia> materias;

  public AlumnoDTO() {}

  public AlumnoDTO(Long id, String rut, String nombre, String direccion, List<Materia> materias) {
    this.id = id;
    this.rut = rut;
    this.nombre = nombre;
    this.direccion = direccion;
    this.materias = materias;
  }

  public AlumnoDTO(Alumno alumno) {
    this.id = alumno.getId();
    this.rut = alumno.getRut();
    this.nombre = alumno.getNombre();
    this.direccion = alumno.getDireccion();
    this.materias = alumno.getMaterias();
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

  public List<Materia> getMaterias() {
    return materias;
  }

  public void setMaterias(List<Materia> materias) {
    this.materias = materias;
  }
}
