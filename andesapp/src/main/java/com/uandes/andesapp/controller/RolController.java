package com.uandes.andesapp.controller;

import com.uandes.andesapp.dtos.RolDTO;
import com.uandes.andesapp.services.RolServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/roles")
public class RolController {

  private final RolServiceImp rolService;

  @Autowired
  public RolController(RolServiceImp rolService) {
    this.rolService = rolService;
  }

  @GetMapping
  public String listarRoles(Model model) {
    model.addAttribute("roles", rolService.findAll());
    return "roles/listar";
  }

  @GetMapping("/crear")
  public String mostrarFormularioCreacion(Model model) {
    model.addAttribute("rol", new RolDTO());
    return "roles/crear";
  }

  @PostMapping("/crear")
  public String crearRol(@ModelAttribute RolDTO rolDTO, RedirectAttributes redirectAttributes) {
    try {
      rolService.save(rolDTO);
      redirectAttributes.addFlashAttribute("success", "Rol creado exitosamente");
    } catch (RuntimeException e) {
      redirectAttributes.addFlashAttribute("error", e.getMessage());
    }
    return "redirect:/roles";
  }

  @GetMapping("/editar/{id}")
  public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
    RolDTO rol = rolService.findById(id);
    model.addAttribute("rol", rol);
    return "roles/editar";
  }

  @PostMapping("/editar/{id}")
  public String actualizarRol(@PathVariable Long id, @ModelAttribute RolDTO rolDTO, RedirectAttributes redirectAttributes) {
    try {
      rolService.update(rolDTO);
      redirectAttributes.addFlashAttribute("success", "Rol actualizado exitosamente");
    } catch (RuntimeException e) {
      redirectAttributes.addFlashAttribute("error", e.getMessage());
    }
    return "redirect:/roles";
  }

  @GetMapping("/eliminar/{id}")
  public String eliminarRol(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    try {
      rolService.delete(id);
      redirectAttributes.addFlashAttribute("success", "Rol eliminado exitosamente");
    } catch (RuntimeException e) {
      redirectAttributes.addFlashAttribute("error", e.getMessage());
    }
    return "redirect:/roles";
  }
}