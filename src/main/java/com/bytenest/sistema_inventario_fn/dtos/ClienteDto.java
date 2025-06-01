package com.bytenest.sistema_inventario_fn.dtos;

import com.bytenest.sistema_inventario_fn.enums.EstadosUf;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteDto(
        @NotBlank
        String nomeCliente,

        @NotBlank
        @Email
        String emailCliente,

        @NotBlank
        String ddd,

        @NotBlank
        String numero,

        @NotBlank
        String cep,

        @NotBlank
        String cidade,

        @NotBlank
        String bairro,

        @NotBlank
        EstadosUf uf
) {
}
