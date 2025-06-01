package com.bytenest.sistema_inventario_fn.model.stock;

import com.bytenest.sistema_inventario_fn.enums.TipoMovimentacao;
import com.bytenest.sistema_inventario_fn.model.entities.FuncionarioModel;
import com.bytenest.sistema_inventario_fn.model.entities.PecaModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "MOVIMENTACOES")
public class MovimentacaoModel {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idMovimentacao;

    @Enumerated(value = EnumType.STRING)
    private TipoMovimentacao tipo;

    private LocalDate data;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(unique = true)
    private String notaFiscal;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_peca")
    private PecaModel peca;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_func")
    private FuncionarioModel funcionario;

}
