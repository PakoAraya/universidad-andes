package com.uandes.andesapp.controller;

import com.uandes.andesapp.dtos.MateriaDTO;
import com.uandes.andesapp.services.AlumnoServiceImp;
import com.uandes.andesapp.services.MateriaServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/materias")
public class MateriaController {

  private final MateriaServiceImp materiaService;
  private final AlumnoServiceImp alumnoService;

  @Autowired
  public MateriaController(MateriaServiceImp materiaService, AlumnoServiceImp alumnoService) {
    this.materiaService = materiaService;
    this.alumnoService = alumnoService;
  }

  @GetMapping
  public String listarMaterias(Model model) {
    model.addAttribute("materias", materiaService.findAll());
    return "materias/listar";
  }

  @GetMapping("/crear")
  public String mostrarFormularioCreacion(Model model) {
    model.addAttribute("materia", new MateriaDTO());
    model.addAttribute("alumnos", alumnoService.findAll());
    return "materias/crear";
  }

  @PostMapping("/crear")
  public String crearMateria(@ModelAttribute MateriaDTO materiaDTO, RedirectAttributes redirectAttributes) {
    try {
      materiaService.save(materiaDTO);
      redirectAttributes.addFlashAttribute("success", "Materia creada exitosamente");
    } catch (RuntimeException e) {
      redirectAttributes.addFlashAttribute("error", e.getMessage());
    }
    return "redirect:/materias";
  }

  @GetMapping("/editar/{id}")
  public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
    MateriaDTO materia = materiaService.findById(id);
    model.addAttribute("materia", materia);
    model.addAttribute("alumnos", alumnoService.findAll());
    return "materias/editar";
  }

  @PostMapping("/editar/{id}")
  public String actualizarMateria(@PathVariable Long id, @ModelAttribute MateriaDTO materiaDTO, RedirectAttributes redirectAttributes) {
    try {
      materiaService.update(id, materiaDTO);
      redirectAttributes.addFlashAttribute("success", "Materia actualizada exitosamente");
    } catch (RuntimeException e) {
      redirectAttributes.addFlashAttribute("error", e.getMessage());
    }
    return "redirect:/materias";
  }

  @GetMapping("/eliminar/{id}")
  public String eliminarMateria(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    try {
      materiaService.deleteById(id);
      redirectAttributes.addFlashAttribute("success", "Materia eliminada exitosamente");
    } catch (RuntimeException e) {
      redirectAttributes.addFlashAttribute("error", e.getMessage());
    }
    return "redirect:/materias";
  }
}