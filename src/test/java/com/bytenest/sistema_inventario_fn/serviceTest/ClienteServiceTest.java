package com.bytenest.sistema_inventario_fn.serviceTest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import com.bytenest.sistema_inventario_fn.dtos.ClienteDto;
import com.bytenest.sistema_inventario_fn.enums.EstadosUf;
import com.bytenest.sistema_inventario_fn.model.entities.ClienteModel;
import com.bytenest.sistema_inventario_fn.repositories.ClienteRepository;
import com.bytenest.sistema_inventario_fn.services.ClienteService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {
    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteService clienteService;

    @Test
    void deveSalvarClienteComTelefoneEEndereco() {
        // Mock DTO com dados de teste
        ClienteDto dto = new ClienteDto(
                "fulano@teste.com",
                "Fulano",
                "11",
                "999999999",
                "12345-678",
                "São Paulo",
                "Glicério",
                 EstadosUf.SP

        );

        // Mock comportamento do save (simulando que o JPA gerou um id)
        when(clienteRepository.save(any(ClienteModel.class))).thenAnswer(invocation -> {
            ClienteModel cliente = invocation.getArgument(0);
            cliente.setIdCliente(UUID.randomUUID());
            return cliente;
        });

        // Chama o método que queremos testar
        ClienteModel clienteSalvo = clienteService.salvarCliente(dto);

        // Verificações básicas
        assertNotNull(clienteSalvo);
        assertNotNull(clienteSalvo.getIdCliente());

        assertEquals(dto.emailCliente(), clienteSalvo.getEmailCliente());
        assertEquals(dto.nomeCliente(), clienteSalvo.getNomeCliente());

        // Verifica se telefone e endereço foram adicionados
        assertFalse(clienteSalvo.getTelefones().isEmpty());
        assertFalse(clienteSalvo.getEnderecos().isEmpty());

        // Verifica dados do telefone
        assertEquals(dto.ddd(), clienteSalvo.getTelefones().get(0).getDdd());
        assertEquals(dto.numero(), clienteSalvo.getTelefones().get(0).getNumero());

        // Verifica dados do endereço
        assertEquals(dto.uf(), clienteSalvo.getEnderecos().get(0).getUf());
        assertEquals(dto.cep(), clienteSalvo.getEnderecos().get(0).getCep());
        assertEquals(dto.cidade(), clienteSalvo.getEnderecos().get(0).getCidade());
        assertEquals(dto.bairro(), clienteSalvo.getEnderecos().get(0).getBairro());

        // Verifica se save foi chamado 1 vez
        verify(clienteRepository, times(1)).save(any(ClienteModel.class));
    }
}
