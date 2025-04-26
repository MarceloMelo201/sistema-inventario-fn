package com.bytenest.sistema_inventario_fn.repositories;


import com.bytenest.sistema_inventario_fn.model.FuncionarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<FuncionarioModel, UUID> {
}
