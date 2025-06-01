package com.bytenest.sistema_inventario_fn.repositories;

import com.bytenest.sistema_inventario_fn.model.stock.MovimentacaoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MovimentacoesRepository extends JpaRepository<MovimentacaoModel, UUID> {
}
