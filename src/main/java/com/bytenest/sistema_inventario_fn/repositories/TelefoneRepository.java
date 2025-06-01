package com.bytenest.sistema_inventario_fn.repositories;

import com.bytenest.sistema_inventario_fn.model.component.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TelefoneRepository extends JpaRepository<Telefone, UUID> {

    Optional<Telefone> findByNumero(String numero);
}
