package com.uandes.andesapp.interfaces;

import com.uandes.andesapp.dtos.UsuarioDTO;
import java.util.List;

public interface IUsuarioService {
  UsuarioDTO save(UsuarioDTO usuarioDTO);
  UsuarioDTO findById(Long id);
  List<UsuarioDTO> findAll();
  void delete(Long id);
  void update(UsuarioDTO usuarioDTO);
}
