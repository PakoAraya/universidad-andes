package com.uandes.andesapp.services;

import com.uandes.andesapp.dtos.AlumnoDTO;
import com.uandes.andesapp.interfaces.IAlumnoService;
import com.uandes.andesapp.models.Alumno;
import com.uandes.andesapp.models.Materia;
import com.uandes.andesapp.repositories.AlumnoRepositoryJPA;
import com.uandes.andesapp.repositories.MateriaRepositoryJPA;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlumnoServiceImp implements IAlumnoService {

  private final AlumnoRepositoryJPA alumnoRepositoryJPA;
  private final MateriaRepositoryJPA materiaRepositoryJPA;

  public AlumnoServiceImp(AlumnoRepositoryJPA alumnoRepositoryJPA, MateriaRepositoryJPA materiaRepositoryJPA) {
    this.alumnoRepositoryJPA = alumnoRepositoryJPA;
    this.materiaRepositoryJPA = materiaRepositoryJPA;
  }

  @Override
  public AlumnoDTO save(AlumnoDTO alumnoDTO) {
    // Validar si el RUT ya existe
    Optional<Alumno> alumnoExistente = alumnoRepositoryJPA.findByRut(alumnoDTO.getRut());
    if (alumnoExistente.isPresent()) {
      throw new RuntimeException("El RUT " + alumnoDTO.getRut() + " ya está registrado.");
    }

    Alumno alumno = new Alumno();
    alumno.setRut(alumnoDTO.getRut());
    alumno.setNombre(alumnoDTO.getNombre());
    alumno.setDireccion(alumnoDTO.getDireccion());

    // Convertir MateriaDTO a Materia y asignarlas al alumno
    List<Materia> materias = alumnoDTO.getMaterias().stream()
            .map(materiaDTO -> {
              Materia materia = new Materia();
              materia.setNombre(materiaDTO.getNombre());
              materia.setAlumno(alumno);  // Asignar el alumno a la materia
              return materia;
            })
            .collect(Collectors.toList());

    alumno.setMaterias(materias);  // Asignar las materias al alumno

    Alumno alumnoGuardado = alumnoRepositoryJPA.save(alumno);
    return new AlumnoDTO(alumnoGuardado);
  }

  @Override
  public AlumnoDTO findById(Long id) {
    Alumno alumno = alumnoRepositoryJPA.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encuentra el alumno con id: " + id));
    return new AlumnoDTO(alumno);
  }

  @Override
  public List<AlumnoDTO> findAll() {
    List<Alumno> alumnos = alumnoRepositoryJPA.findAll();
    return alumnos.stream()
            .map(AlumnoDTO::new)
            .collect(Collectors.toList());
  }

  @Override
  public void deleteById(Long id) {
    alumnoRepositoryJPA.deleteById(id);
  }

  @Override
  public AlumnoDTO update(Long id, AlumnoDTO alumnoDTO) {
    Alumno alumno = alumnoRepositoryJPA.findById(id)
            .orElseThrow(() -> new RuntimeException("No se encuentra el alumno con id: " + id));
    alumno.setRut(alumnoDTO.getRut());
    alumno.setNombre(alumnoDTO.getNombre());
    alumno.setDireccion(alumnoDTO.getDireccion());

    // Actualizar las materias del alumno
    List<Materia> materias = alumnoDTO.getMaterias().stream()
            .map(materiaDTO -> {
              Materia materia = materiaRepositoryJPA.findById(materiaDTO.getId())
                      .orElseThrow(() -> new RuntimeException("Materia no encontrada"));
              materia.setNombre(materiaDTO.getNombre());
              materia.setAlumno(alumno);  // Asignar el alumno a la materia
              return materia;
            })
            .collect(Collectors.toList());

    alumno.setMaterias(materias);  // Asignar las materias actualizadas al alumno

    Alumno alumnoActualizado = alumnoRepositoryJPA.save(alumno);
    return new AlumnoDTO(alumnoActualizado);
  }
}