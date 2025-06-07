package com.bytenest.sistema_inventario_fn.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MovimentacaoDto(

        @NotBlank
        LocalDate data,

        @NotNull
        Integer quantidade,

        @NotBlank
        String notaFiscal,

        @NotBlank
        String observacoes,

        @NotNull
        PecaDto pecaDto
) {
}
