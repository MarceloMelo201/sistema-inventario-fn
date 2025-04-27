package com.bytenest.sistema_inventario_fn.dtos;

import com.bytenest.sistema_inventario_fn.enums.Cargo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioDto(

        @NotBlank
        String email,

        @NotBlank
        String senha,

        @NotNull
        Cargo cargo

) {
}
