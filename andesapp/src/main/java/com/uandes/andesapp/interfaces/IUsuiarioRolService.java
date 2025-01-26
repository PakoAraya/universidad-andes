package com.uandes.andesapp.interfaces;

import com.uandes.andesapp.dtos.UsuarioRolDTO;
import java.util.List;

public interface IUsuiarioRolService {
  UsuarioRolDTO save(UsuarioRolDTO usuarioRolDTO);
  UsuarioRolDTO findById(Long id);
  List<UsuarioRolDTO> findAll();
  void deleteById(Long id);
  UsuarioRolDTO update(Long id, UsuarioRolDTO usuarioRolDTO);
  List<UsuarioRolDTO> findByUsuarioId(Long usuarioId);
  List<UsuarioRolDTO> findByRolId(Long rolId);
}
