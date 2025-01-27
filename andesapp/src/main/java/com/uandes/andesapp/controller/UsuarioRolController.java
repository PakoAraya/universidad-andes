package com.uandes.andesapp.controller;

import com.uandes.andesapp.dtos.UsuarioRolDTO;
import com.uandes.andesapp.services.RolServiceImp;
import com.uandes.andesapp.services.UsuarioRolServiceImp;
import com.uandes.andesapp.services.UsuarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios-roles")
public class UsuarioRolController {

  private final UsuarioRolServiceImp usuarioRolService;
  private final UsuarioServiceImp usuarioService;
  private final RolServiceImp rolService;

  @Autowired
  public UsuarioRolController(UsuarioRolServiceImp usuarioRolService, UsuarioServiceImp usuarioService, RolServiceImp rolService) {
    this.usuarioRolService = usuarioRolService;
    this.usuarioService = usuarioService;
    this.rolService = rolService;
  }

  @GetMapping
  public String listarUsuarioRoles(Model model) {
    model.addAttribute("usuariosRoles", usuarioRolService.findAll());
    return "usuarios-roles/listar";
  }

  @GetMapping("/crear")
  public String mostrarFormularioCreacion(Model model) {
    model.addAttribute("usuarioRol", new UsuarioRolDTO());
    model.addAttribute("usuarios", usuarioService.findAll());
    model.addAttribute("roles", rolService.findAll());
    return "usuarios-roles/crear";
  }

  @PostMapping("/crear")
  public String crearUsuarioRol(@ModelAttribute UsuarioRolDTO usuarioRolDTO, RedirectAttributes redirectAttributes) {
    try {
      usuarioRolService.save(usuarioRolDTO);
      redirectAttributes.addFlashAttribute("success", "Asignación de rol creada exitosamente");
    } catch (RuntimeException e) {
      redirectAttributes.addFlashAttribute("error", e.getMessage());
    }
    return "redirect:/usuarios-roles";
  }

  @GetMapping("/eliminar/{id}")
  public String eliminarUsuarioRol(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    try {
      usuarioRolService.deleteById(id);
      redirectAttributes.addFlashAttribute("success", "Asignación de rol eliminada exitosamente");
    } catch (RuntimeException e) {
      redirectAttributes.addFlashAttribute("error", e.getMessage());
    }
    return "redirect:/usuarios-roles";
  }
}