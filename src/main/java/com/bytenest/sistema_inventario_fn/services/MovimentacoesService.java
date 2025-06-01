package com.bytenest.sistema_inventario_fn.services;

import com.bytenest.sistema_inventario_fn.repositories.FuncionarioRepository;
import com.bytenest.sistema_inventario_fn.repositories.MovimentacoesRepository;
import com.bytenest.sistema_inventario_fn.repositories.PecaRepository;
import org.springframework.stereotype.Service;

@Service
public class MovimentacoesService {

    private final PecaRepository pecaRepository;
    private final MovimentacoesRepository movimentacoesRepository;
    private final FuncionarioRepository funcionarioRepository;

    public MovimentacoesService(PecaRepository pecaRepository, MovimentacoesRepository movimentacoesRepository, FuncionarioRepository funcionarioRepository) {
        this.pecaRepository = pecaRepository;
        this.movimentacoesRepository = movimentacoesRepository;
        this.funcionarioRepository = funcionarioRepository;
    }
}
