package com.uandes.andesapp.controller;

import com.uandes.andesapp.dtos.RolDTO;
import com.uandes.andesapp.services.RolServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolRestController {

  @Autowired
  private RolServiceImp rolService;

  @GetMapping
  public List<RolDTO> findAll() {
    return rolService.findAll();
  }

  @GetMapping("/{id}")
  public RolDTO findById(@PathVariable Long id) {
    return rolService.findById(id);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public RolDTO save(@RequestBody RolDTO rolDTO) {
    return rolService.save(rolDTO);
  }

  @PutMapping("/{id}")
  public RolDTO update(@PathVariable Long id, @RequestBody RolDTO rolDTO) {
    return rolService.update(rolDTO);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable Long id) {
    rolService.delete(id);
  }
}