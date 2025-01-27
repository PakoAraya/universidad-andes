package com.uandes.andesapp.controller;

import com.uandes.andesapp.dtos.MateriaDTO;
import com.uandes.andesapp.services.MateriaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/materias")
public class MateriaRestController {

  @Autowired
  private MateriaServiceImp materiaService;

  @GetMapping
  public List<MateriaDTO> findAll() {
    return materiaService.findAll();
  }

  @GetMapping("/{id}")
  public MateriaDTO findById(@PathVariable Long id) {
    return materiaService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MateriaDTO save(@RequestBody MateriaDTO materiaDTO) {
    return materiaService.save(materiaDTO);
  }

  @PutMapping("/{id}")
  public MateriaDTO update(@PathVariable Long id, @RequestBody MateriaDTO materiaDTO) {
    return materiaService.update(id, materiaDTO);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable Long id) {
    materiaService.deleteById(id);
  }

  @GetMapping("/alumno/{alumnoId}")
  public List<MateriaDTO> findByAlumnoId(@PathVariable Long alumnoId) {
    return materiaService.findByAlumnoId(alumnoId);
  }
}