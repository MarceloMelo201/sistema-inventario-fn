package com.bytenest.sistema_inventario_fn.dtos;

import com.bytenest.InvetarioApi.enums.Cargos;
import jakarta.validation.constraints.NotBlank;

public record CriarUsuarioDto(

        @NotBlank
        String email,

        @NotBlank
        String senha,

        Cargos cargo

) {
}
