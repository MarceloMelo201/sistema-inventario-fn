package com.bytenest.sistema_inventario_fn.model;

import com.bytenest.sistema_inventario_fn.enums.EstadosUf;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CLIENTES")
public class ClienteModel {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCliente;

    private String nomeCliente;

    @Column(unique = true)
    private String emailCliente;

    private String telefone;
    private String cidade;
    private String bairro;
    private String rua;

    @Enumerated(EnumType.STRING)
    private EstadosUf uf;

   /* @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<OrdemServicoModel> ordensDeServico;

    */
}
