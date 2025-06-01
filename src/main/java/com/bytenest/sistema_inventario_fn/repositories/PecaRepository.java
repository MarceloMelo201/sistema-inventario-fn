package com.bytenest.sistema_inventario_fn.repositories;

import com.bytenest.sistema_inventario_fn.model.entities.PecaModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PecaRepository extends JpaRepository<PecaModel, UUID> {

    Optional<PecaModel> findBySku(String sku);
}
