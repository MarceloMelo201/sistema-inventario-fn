package com.bytenest.sistema_inventario_fn.model.entities;

import com.bytenest.sistema_inventario_fn.enums.Cargo;
import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "usuarios")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModel {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String email;

    private String senha;

    @Enumerated(value = EnumType.STRING)
    private Cargo cargo;

}
