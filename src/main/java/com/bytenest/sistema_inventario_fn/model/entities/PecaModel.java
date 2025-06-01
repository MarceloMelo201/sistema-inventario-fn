package com.bytenest.sistema_inventario_fn.model.entities;

import com.bytenest.sistema_inventario_fn.model.stock.MovimentacaoModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PECAS")
public class PecaModel {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idPeca;

    private String nome;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor;

    @Column(unique = true)
    private String sku;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    private Integer quantidadeTotal;

    @OneToMany(mappedBy = "peca", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<MovimentacaoModel> movimentacoes;

}
