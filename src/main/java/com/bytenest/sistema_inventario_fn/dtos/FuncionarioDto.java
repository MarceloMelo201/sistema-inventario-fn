package com.bytenest.sistema_inventario_fn.dtos;

import com.bytenest.sistema_inventario_fn.enums.EstadosUf;
import com.bytenest.sistema_inventario_fn.enums.StatusFuncionario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record FuncionarioDto(

        @NotBlank
        String nome,

        @NotBlank
        String cpf,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String ddd,

        @NotBlank
        String numero,

        @NotBlank
        StatusFuncionario status,

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
