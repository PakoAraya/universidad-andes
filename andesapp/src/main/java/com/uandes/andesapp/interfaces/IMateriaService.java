package com.uandes.andesapp.interfaces;

import com.uandes.andesapp.dtos.MateriaDTO;
import java.util.List;

public interface IMateriaService {
  MateriaDTO save(MateriaDTO materiaDTO);
  MateriaDTO findById(Long id);
  List<MateriaDTO> findAll();
  void deleteById(Long id);
  MateriaDTO update(Long id, MateriaDTO materiaDTO);
  List<MateriaDTO> findByAlumnoId(Long alumnoId);
}
