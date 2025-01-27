package com.uandes.andesapp.controller;

import com.uandes.andesapp.dtos.AlumnoDTO;
import com.uandes.andesapp.services.AlumnoServiceImp;
import com.uandes.andesapp.services.MateriaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/alumnos")
public class AlumnoController {

  private final AlumnoServiceImp alumnoService;
  private final MateriaServiceImp materiaService;

  @Autowired
  public AlumnoController(AlumnoServiceImp alumnoService, MateriaServiceImp materiaService) {
    this.alumnoService = alumnoService;
    this.materiaService = materiaService;
  }

  @GetMapping
  public String listarAlumnos(Model model) {
    model.addAttribute("alumnos", alumnoService.findAll());
    return "alumnos/listar";
  }

  @GetMapping("/crear")
  public String mostrarFormularioCreacion(Model model) {
    model.addAttribute("alumno", new AlumnoDTO());
    return "alumnos/crear";
  }

  @PostMapping("/crear")
  public String crearAlumno(@ModelAttribute AlumnoDTO alumnoDTO, RedirectAttributes redirectAttributes) {
    try {
      alumnoService.save(alumnoDTO);
      redirectAttributes.addFlashAttribute("success", "Alumno creado exitosamente");
    } catch (RuntimeException e) {
      redirectAttributes.addFlashAttribute("error", e.getMessage());
    }
    return "redirect:/alumnos";
  }

  @GetMapping("/editar/{id}")
  public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
    AlumnoDTO alumno = alumnoService.findById(id);
    model.addAttribute("alumno", alumno);
    return "alumnos/editar";
  }

  @PostMapping("/editar/{id}")
  public String actualizarAlumno(@PathVariable Long id, @ModelAttribute AlumnoDTO alumnoDTO, RedirectAttributes redirectAttributes) {
    try {
      alumnoService.update(id, alumnoDTO);
      redirectAttributes.addFlashAttribute("success", "Alumno actualizado exitosamente");
    } catch (RuntimeException e) {
      redirectAttributes.addFlashAttribute("error", e.getMessage());
    }
    return "redirect:/alumnos";
  }

  @GetMapping("/eliminar/{id}")
  public String eliminarAlumno(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    try {
      alumnoService.deleteById(id);
      redirectAttributes.addFlashAttribute("success", "Alumno eliminado exitosamente");
    } catch (RuntimeException e) {
      redirectAttributes.addFlashAttribute("error", e.getMessage());
    }
    return "redirect:/alumnos";
  }
}