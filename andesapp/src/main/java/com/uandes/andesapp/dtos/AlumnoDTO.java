package com.uandes.andesapp.dtos;

import com.uandes.andesapp.models.Alumno;
import java.util.List;
import java.util.stream.Collectors;

public class AlumnoDTO {
  private Long id;
  private String rut;
  private String nombre;
  private String direccion;
  private List<MateriaDTO> materias;

  public AlumnoDTO() {}

  public AlumnoDTO(Long id, String rut, String nombre, String direccion, List<MateriaDTO> materias) {
    this.id = id;
    this.rut = rut;
    this.nombre = nombre;
    this.direccion = direccion;
    this.materias = materias;
  }

  // Constructor que recibe una entidad Alumno y la convierte a DTO usando lambda
  public AlumnoDTO(Alumno alumno) {
    this.id = alumno.getId();
    this.rut = alumno.getRut();
    this.nombre = alumno.getNombre();
    this.direccion = alumno.getDireccion();
    this.materias = alumno.getMateria().stream()
            .map(materia -> new MateriaDTO(materia))  // Función lambda
            .collect(Collectors.toList());  // Convertir Stream a List
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

  // Cambiado de getMateriaDTO a getMaterias
  public List<MateriaDTO> getMaterias() {
    return materias;
  }

  // Cambiado de setMateriaDTO a setMaterias
  public void setMaterias(List<MateriaDTO> materias) {
    this.materias = materias;
  }
}