package com.uandes.andesapp.controller;

import com.uandes.andesapp.dtos.AlumnoDTO;
import com.uandes.andesapp.services.AlumnoServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoRestController {

  @Autowired
  private AlumnoServiceImp alumnoService;

  @GetMapping
  public List<AlumnoDTO> findAll() {
    return alumnoService.findAll();
  }

  @GetMapping("/{id}")
  public AlumnoDTO findById(@PathVariable Long id) {
    return alumnoService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public AlumnoDTO save(@RequestBody AlumnoDTO alumnoDTO) {
    return alumnoService.save(alumnoDTO);
  }

  @PutMapping("/{id}")
  public AlumnoDTO update(@PathVariable Long id, @RequestBody AlumnoDTO alumnoDTO) {
    return alumnoService.update(id, alumnoDTO);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteById(@PathVariable Long id) {
    alumnoService.deleteById(id);
  }
}