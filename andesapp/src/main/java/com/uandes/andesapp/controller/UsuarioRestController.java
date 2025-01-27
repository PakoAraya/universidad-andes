package com.uandes.andesapp.controller;

import com.uandes.andesapp.dtos.UsuarioDTO;
import com.uandes.andesapp.services.UsuarioServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioRestController {

  @Autowired
  private UsuarioServiceImp usuarioService;

  @GetMapping
  public List<UsuarioDTO> findAll() {
    return usuarioService.findAll();
  }

  @GetMapping("/{id}")
  public UsuarioDTO findById(@PathVariable Long id) {
    return usuarioService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UsuarioDTO save(@RequestBody UsuarioDTO usuarioDTO) {
    return usuarioService.save(usuarioDTO);
  }

  @PutMapping("/{id}")
  public void update(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
    usuarioService.update(usuarioDTO);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    usuarioService.delete(id);
  }
}