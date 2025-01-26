package com.uandes.andesapp.services;

import com.uandes.andesapp.dtos.MateriaDTO;
import com.uandes.andesapp.interfaces.IMateriaService;
import com.uandes.andesapp.models.Alumno;
import com.uandes.andesapp.models.Materia;
import com.uandes.andesapp.repositories.AlumnoRepositoryJPA;
import com.uandes.andesapp.repositories.MateriaRepositoryJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MateriaServiceImp implements IMateriaService {

  @Autowired
  private MateriaRepositoryJPA materiaRepositoryJPA;

  @Autowired
  private AlumnoRepositoryJPA alumnoRepositoryJPA; // Usamos el repositorio directamente

  @Override
  public MateriaDTO save(MateriaDTO materiaDTO) {
    Materia materia = new Materia();
    materia.setNombre(materiaDTO.getNombre());

    // Convertir AlumnoDTO a Alumno usando el repositorio
    if (materiaDTO.getAlumnoDTO() != null) {
      Optional<Alumno> alumnoOptional = alumnoRepositoryJPA.findById(materiaDTO.getAlumnoDTO().getId());
      if (alumnoOptional.isPresent()) {
        materia.setAlumno(alumnoOptional.get());
      } else {
        throw new RuntimeException("Alumno no encontrado con ID: " + materiaDTO.getAlumnoDTO().getId());
      }
    }

    Materia savedMateria = materiaRepositoryJPA.save(materia);
    return new MateriaDTO(savedMateria);
  }

  @Override
  public MateriaDTO findById(Long id) {
    Optional<Materia> materia = materiaRepositoryJPA.findById(id);
    return materia.map(MateriaDTO::new).orElse(null);
  }

  @Override
  public List<MateriaDTO> findAll() {
    List<Materia> materias = materiaRepositoryJPA.findAll();
    return materias.stream()
            .map(MateriaDTO::new)
            .collect(Collectors.toList());
  }

  @Override
  public void deleteById(Long id) {
    materiaRepositoryJPA.deleteById(id);
  }

  @Override
  public MateriaDTO update(Long id, MateriaDTO materiaDTO) {
    Optional<Materia> materiaOptional = materiaRepositoryJPA.findById(id);
    if (materiaOptional.isPresent()) {
      Materia materia = materiaOptional.get();
      materia.setNombre(materiaDTO.getNombre());

      // Convertir AlumnoDTO a Alumno usando el repositorio
      if (materiaDTO.getAlumnoDTO() != null) {
        Optional<Alumno> alumnoOptional = alumnoRepositoryJPA.findById(materiaDTO.getAlumnoDTO().getId());
        if (alumnoOptional.isPresent()) {
          materia.setAlumno(alumnoOptional.get());
        } else {
          throw new RuntimeException("Alumno no encontrado con ID: " + materiaDTO.getAlumnoDTO().getId());
        }
      }

      Materia updatedMateria = materiaRepositoryJPA.save(materia);
      return new MateriaDTO(updatedMateria);
    }
    return null;
  }

  @Override
  public List<MateriaDTO> findByAlumnoId(Long alumnoId) {
    List<Materia> materias = materiaRepositoryJPA.findByAlumnoId(alumnoId);
    return materias.stream()
            .map(MateriaDTO::new)
            .collect(Collectors.toList());
  }
}