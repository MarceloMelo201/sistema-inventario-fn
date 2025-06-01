package com.bytenest.sistema_inventario_fn.repositories;

import com.bytenest.sistema_inventario_fn.model.entities.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClienteRepository extends JpaRepository<ClienteModel, UUID> {
}
