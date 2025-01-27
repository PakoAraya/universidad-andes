package com.uandes.andesapp.controller;

import com.uandes.andesapp.dtos.UsuarioDTO;
import com.uandes.andesapp.services.RolServiceImp;
import com.uandes.andesapp.services.UsuarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

  private final UsuarioServiceImp usuarioService;
  private final RolServiceImp rolService;

  @Autowired
  public UsuarioController(UsuarioServiceImp usuarioService, RolServiceImp rolService) {
    this.usuarioService = usuarioService;
    this.rolService = rolService;
  }

  @GetMapping
  public String listarUsuarios(Model model) {
    model.addAttribute("usuarios", usuarioService.findAll());
    return "usuarios/listar";
  }

  @GetMapping("/crear")
  public String mostrarFormularioCreacion(Model model) {
    model.addAttribute("usuario", new UsuarioDTO());
    model.addAttribute("rolesDisponibles", rolService.findAll());
    return "usuarios/crear";
  }

  @PostMapping("/crear")
  public String crearUsuario(@ModelAttribute UsuarioDTO usuarioDTO, RedirectAttributes redirectAttributes) {
    try {
      usuarioService.save(usuarioDTO);
      redirectAttributes.addFlashAttribute("success", "Usuario creado exitosamente");
    } catch (RuntimeException e) {
      redirectAttributes.addFlashAttribute("error", e.getMessage());
    }
    return "redirect:/usuarios";
  }

  @GetMapping("/editar/{id}")
  public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
    UsuarioDTO usuario = usuarioService.findById(id);
    model.addAttribute("usuario", usuario);
    model.addAttribute("rolesDisponibles", rolService.findAll());
    return "usuarios/editar";
  }

  @PostMapping("/editar/{id}")
  public String actualizarUsuario(@PathVariable Long id, @ModelAttribute UsuarioDTO usuarioDTO, RedirectAttributes redirectAttributes) {
    try {
      usuarioService.update(usuarioDTO);
      redirectAttributes.addFlashAttribute("success", "Usuario actualizado exitosamente");
    } catch (RuntimeException e) {
      redirectAttributes.addFlashAttribute("error", e.getMessage());
    }
    return "redirect:/usuarios";
  }

  @GetMapping("/eliminar/{id}")
  public String eliminarUsuario(@PathVariable Long id, RedirectAttributes redirectAttributes) {
    try {
      usuarioService.delete(id);
      redirectAttributes.addFlashAttribute("success", "Usuario eliminado exitosamente");
    } catch (RuntimeException e) {
      redirectAttributes.addFlashAttribute("error", e.getMessage());
    }
    return "redirect:/usuarios";
  }
}