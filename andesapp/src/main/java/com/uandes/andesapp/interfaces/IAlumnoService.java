package com.uandes.andesapp.interfaces;

import com.uandes.andesapp.dtos.AlumnoDTO;
import java.util.List;

public interface IAlumnoService {
  AlumnoDTO save(AlumnoDTO alumnoDTO);
  AlumnoDTO findById(Long id);
  List<AlumnoDTO> findAll();
  void deleteById(Long id);
  AlumnoDTO update(Long id, AlumnoDTO alumnoDTO);
}
