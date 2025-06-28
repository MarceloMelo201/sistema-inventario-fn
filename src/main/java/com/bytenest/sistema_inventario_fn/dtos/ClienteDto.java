package com.bytenest.sistema_inventario_fn.dtos;

import com.bytenest.sistema_inventario_fn.enums.EstadosUf;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ClienteDto(
        @NotBlank
        String nomeCliente,

        @Email
        String emailCliente,

        @NotBlank
        String numero,


        String cep,

        @NotBlank
        String cidade,

        @NotBlank
        String bairro,

        EstadosUf uf
) {
}
