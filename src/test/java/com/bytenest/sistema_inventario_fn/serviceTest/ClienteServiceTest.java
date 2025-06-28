package com.bytenest.sistema_inventario_fn.serviceTest;

import com.bytenest.sistema_inventario_fn.dtos.ClienteDto;
import com.bytenest.sistema_inventario_fn.model.component.Telefone;
import com.bytenest.sistema_inventario_fn.model.entities.ClienteModel;
import com.bytenest.sistema_inventario_fn.repositories.ClienteRepository;

import com.bytenest.sistema_inventario_fn.services.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    private UUID clienteId;
    private ClienteModel clienteExistente;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        clienteId = UUID.randomUUID();

        clienteExistente = ClienteModel.builder()
                .idCliente(clienteId)
                .nomeCliente("Antigo Nome")
                .emailCliente("antigo@email.com")
                .cep("00000-000")
                .cidade("Cidade Antiga")
                .bairro("Bairro Antigo")
                .uf(null)
                .telefones(new ArrayList<>(List.of(
                        Telefone.builder().numero("111111111").build()
                )))
                .build();

    }

    @Test
    void atualizarCliente_sucesso() {
        // Mock findById para retornar cliente existente
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.of(clienteExistente));

        ClienteDto dtoAtualizado = new ClienteDto(
                "Novo Nome",
                "novo@email.com",
                "999999999",
                "12345-678",
                "Nova Cidade",
                "Novo Bairro",
                clienteExistente.getUf()
        );

        // Mock save para retornar o cliente que recebeu as atualizações
        when(clienteRepository.save(any(ClienteModel.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ClienteModel resultado = clienteService.atualizarCliente(clienteId, dtoAtualizado);

        assertEquals("Novo Nome", resultado.getNomeCliente());
        assertEquals("novo@email.com", resultado.getEmailCliente());
        assertEquals("12345-678", resultado.getCep());
        assertEquals("Nova Cidade", resultado.getCidade());
        assertEquals("Novo Bairro", resultado.getBairro());
        assertEquals("999999999", resultado.getTelefones().get(0).getNumero());

        verify(clienteRepository).save(resultado);
    }

    @Test
    void atualizarCliente_clienteNaoEncontrado_deveLancarExcecao() {
        when(clienteRepository.findById(clienteId)).thenReturn(Optional.empty());

        ClienteDto dto = new ClienteDto(
                "Nome Qualquer",
                "email@exemplo.com",
                "999999999",
                "12345-678",
                "Cidade",
                "Bairro",
                null
        );

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            clienteService.atualizarCliente(clienteId, dto);
        });

        assertEquals("Cliente não encontrado.", exception.getMessage());

        verify(clienteRepository, never()).save(any());
    }
}
