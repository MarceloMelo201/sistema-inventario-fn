package com.bytenest.sistema_inventario_fn.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record PecaDto(

        @NotBlank
        String nome,

        @NotNull
        BigDecimal valor,

        @NotBlank
        String sku,

        @NotBlank
        String descricao,

        @NotNull
        Integer quantidadeTotal
) {
}
