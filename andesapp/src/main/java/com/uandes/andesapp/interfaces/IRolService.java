package com.uandes.andesapp.interfaces;

import com.uandes.andesapp.dtos.RolDTO;
import java.util.List;

public interface IRolService {
  RolDTO save(RolDTO rolDTO);
  RolDTO findById(Long id);
  List<RolDTO> findAll();
  void delete(Long id);
  RolDTO update(RolDTO rolDTO);
}
