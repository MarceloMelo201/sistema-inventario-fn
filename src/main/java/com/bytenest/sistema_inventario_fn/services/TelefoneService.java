package com.bytenest.sistema_inventario_fn.services;

import com.bytenest.sistema_inventario_fn.dtos.ClienteDto;
import com.bytenest.sistema_inventario_fn.model.entities.ClienteModel;
import com.bytenest.sistema_inventario_fn.repositories.TelefoneRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class TelefoneService {

    private final TelefoneRepository telefoneRepository;


    public TelefoneService(TelefoneRepository telefoneRepository) {
        this.telefoneRepository = telefoneRepository;
    }

    @Transactional
    private void atualizarTelefoneCliente(ClienteModel cliente, ClienteDto clienteDto){



    }
}
