package com.bytenest.sistema_inventario_fn.repositories;


import com.bytenest.sistema_inventario_fn.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findByEmail(String email);
}
