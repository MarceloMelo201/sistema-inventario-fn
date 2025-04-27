package com.bytenest.sistema_inventario_fn.dtos;

import com.bytenest.sistema_inventario_fn.enums.StatusFuncionario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FuncionarioDto(

        @NotBlank
        String nome,

        @NotBlank
        String cpf,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        StatusFuncionario status

) {
}
