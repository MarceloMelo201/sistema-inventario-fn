package com.bytenest.sistema_inventario_fn.services;

import com.bytenest.sistema_inventario_fn.dtos.FuncionarioDto;
import com.bytenest.sistema_inventario_fn.model.component.Telefone;
import com.bytenest.sistema_inventario_fn.model.entities.FuncionarioModel;
import com.bytenest.sistema_inventario_fn.repositories.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FuncionarioService {
    private final FuncionarioRepository funcionarioRepository;

    @Autowired
    public FuncionarioService(FuncionarioRepository FuncionarioRepository) {
        this.funcionarioRepository = FuncionarioRepository;
    }

    @Transactional
    public FuncionarioModel salvarFuncionario(FuncionarioDto funcionarioDto){
        var funcionarioModel = FuncionarioModel.builder()
                .nome(funcionarioDto.nome())
                .cpf(funcionarioDto.cpf())
                .email(funcionarioDto.email())
                .cep(funcionarioDto.cep())
                .uf(funcionarioDto.uf())
                .cidade(funcionarioDto.cidade())
                .bairro(funcionarioDto.bairro())
                .build();

        Telefone telefone = Telefone.builder()
                .numero(funcionarioDto.numero())
                .build();

        funcionarioModel.addTelefone(telefone);
        return funcionarioRepository.save(funcionarioModel);
    }

    public List<FuncionarioModel> listarTodosOsFuncionarios(){
        List<FuncionarioModel> listFuncionario = funcionarioRepository.findAll();
        return listFuncionario;
    }

    public FuncionarioModel listarFuncionario(UUID id){
        FuncionarioModel funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));
        return funcionario;
    }

    @Transactional
    public FuncionarioModel atualizarFuncionario(UUID id, FuncionarioDto funcionarioDto){
        FuncionarioModel funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));

        funcionario.setNome(funcionarioDto.nome());
        funcionario.setCpf(funcionarioDto.cpf());
        funcionario.setEmail(funcionarioDto.email());
        funcionario.setStatusFuncionario(funcionarioDto.status());
        funcionario.setCep(funcionarioDto.cep());
        funcionario.setUf(funcionarioDto.uf());
        funcionario.setCidade(funcionarioDto.cidade());
        funcionario.setBairro(funcionarioDto.bairro());

        funcionario.getTelefones().clear();
        Telefone telefone = Telefone.builder()
                .numero(funcionarioDto.numero())
                .build();

        telefone.setFuncionario(funcionario);
        funcionario.setTelefones(List.of(telefone));

        return funcionarioRepository.save(funcionario);
    }

    @Transactional
    public void deletarFuncionario(UUID id){
        FuncionarioModel funcionario = funcionarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado."));

        funcionarioRepository.delete(funcionario);
    }

}

