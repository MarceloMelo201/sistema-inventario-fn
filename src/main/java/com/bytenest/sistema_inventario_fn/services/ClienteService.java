package com.bytenest.sistema_inventario_fn.services;

import com.bytenest.sistema_inventario_fn.dtos.ClienteDto;
import com.bytenest.sistema_inventario_fn.model.component.Telefone;
import com.bytenest.sistema_inventario_fn.model.entities.ClienteModel;
import com.bytenest.sistema_inventario_fn.repositories.ClienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClienteService {
    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;

    }

    @Transactional
    public ClienteModel salvarCliente(ClienteDto clienteDto){
        var clienteModel = getClienteModel(clienteDto);
        return clienteRepository.save(clienteModel);
    }

    private static ClienteModel getClienteModel(ClienteDto clienteDto) {
        var clienteModel = ClienteModel.builder()
                .emailCliente(clienteDto.emailCliente())
                .nomeCliente(clienteDto.nomeCliente())
                .cep(clienteDto.cep())
                .uf(clienteDto.uf())
                .cidade(clienteDto.cidade())
                .bairro(clienteDto.bairro())
                .build();

        Telefone telefone = Telefone.builder()
                .ddd(clienteDto.ddd())
                .numero(clienteDto.numero())
                .build();

        clienteModel.addTelefone(telefone);

        return clienteModel;
    }

    public List<ClienteModel> listarTodosOsClientes(){
        List<ClienteModel> listCliente = clienteRepository.findAll();
        return listCliente;
    }

    public ClienteModel listarCliente(UUID id){
        ClienteModel cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
        return cliente;
    }

    @Transactional
    public ClienteModel atualizarCliente(UUID id, ClienteDto clienteDto){
        ClienteModel cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));

        cliente.setNomeCliente(clienteDto.nomeCliente());
        cliente.setEmailCliente(clienteDto.emailCliente());
        cliente.setCep(clienteDto.cep());
        cliente.setUf(clienteDto.uf());
        cliente.setCidade(clienteDto.cidade());
        cliente.setBairro(clienteDto.bairro());

        cliente.getTelefones().clear();
        Telefone telefone = Telefone.builder()
                .ddd(clienteDto.ddd())
                .numero(clienteDto.numero())
                .build();

        telefone.setCliente(cliente);
        cliente.setTelefones(List.of(telefone));

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void deletarCliente(UUID id){
        ClienteModel cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado."));
        clienteRepository.delete(cliente);
    }
}
