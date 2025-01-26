package com.uandes.andesapp.dtos;

import com.uandes.andesapp.models.Materia;

public class MateriaDTO {
  private Long id;
  private String nombre;
  private AlumnoDTO alumnoDTO;

  public MateriaDTO() {}

  public MateriaDTO(Long id, String nombre, AlumnoDTO alumnoDTO) {
    this.id = id;
    this.nombre = nombre;
    this.alumnoDTO = alumnoDTO;
  }

  // Constructor que recibe una entidad Materia y la convierte a DTO
  public MateriaDTO(Materia materia) {
    this.id = materia.getId();
    this.nombre = materia.getNombre();
    this.alumnoDTO = new AlumnoDTO(materia.getAlumno());  // Convertimos Alumno a AlumnoDTO
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

  public AlumnoDTO getAlumnoDTO() {
    return alumnoDTO;
  }

  public void setAlumnoDTO(AlumnoDTO alumnoDTO) {
    this.alumnoDTO = alumnoDTO;
  }
}