package com.bytenest.sistema_inventario_fn.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FuncionarioDto(

        @NotBlank
        String nome,

        @NotBlank
        String cpf,

        @NotBlank
        String cargo,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotNull
        Boolean estaAtivo

) {
}
