package com.bytenest.sistema_inventario_fn.model.entities;

import com.bytenest.sistema_inventario_fn.enums.EstadosUf;
import com.bytenest.sistema_inventario_fn.enums.StatusFuncionario;
import com.bytenest.sistema_inventario_fn.model.component.Telefone;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FUNCIONARIOS")
public class FuncionarioModel {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idFunc;

    private String nome;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true, nullable = false)
    private String email;

    private String cep;

    @Enumerated(value = EnumType.STRING)
    private EstadosUf uf;

    private String cidade;
    private String bairro;

    @Enumerated(value = EnumType.STRING)
    private StatusFuncionario statusFuncionario;

    @Builder.Default
    @OneToMany(mappedBy = "funcionario", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Telefone> telefones = new ArrayList<>();

    public void addTelefone(Telefone telefone) {
        telefones.add(telefone);
        telefone.setFuncionario(this);
    }


}
