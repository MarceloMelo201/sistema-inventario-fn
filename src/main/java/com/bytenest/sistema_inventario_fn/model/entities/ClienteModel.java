package com.bytenest.sistema_inventario_fn.model.entities;

import com.bytenest.sistema_inventario_fn.enums.EstadosUf;
import com.bytenest.sistema_inventario_fn.model.component.Telefone;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CLIENTES")
public class ClienteModel {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idCliente;

    private String nomeCliente;

    @Column(unique = true)
    private String emailCliente;

    private String cep;

    @Enumerated(value = EnumType.STRING)
    private EstadosUf uf;

    private String cidade;
    private String bairro;

    @Builder.Default
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Telefone> telefones = new ArrayList<>();

    public void addTelefone(Telefone telefone) {
        telefones.add(telefone);
        telefone.setCliente(this);
    }

}
