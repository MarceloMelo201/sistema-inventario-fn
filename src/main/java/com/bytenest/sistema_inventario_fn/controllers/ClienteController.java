package com.bytenest.sistema_inventario_fn.controllers;

import com.bytenest.sistema_inventario_fn.dtos.ClienteDto;
import com.bytenest.sistema_inventario_fn.model.ClienteModel;
import com.bytenest.sistema_inventario_fn.repositories.ClienteRepository;
import com.bytenest.sistema_inventario_fn.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ClienteService clienteService;

    @PostMapping("/clientes")
    public ResponseEntity<?> salvarCliente(@RequestBody @Valid ClienteDto clienteDto) {
        return clienteService.salvarCliente(clienteDto);
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteModel>> listarTodasOsClientes(){
        return clienteService.listarTodasOsClientes();
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Object> listarCliente(@PathVariable(value = "id") UUID id){
        return clienteService.listarCliente(id);
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Object> atualizarCliente(@PathVariable (value = "id") UUID id,
                                                       @RequestBody @Valid ClienteDto clienteDto) {
        return clienteService.atualizarCliente(id, clienteDto);
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Object> deletarCliente(@PathVariable (value = "id") UUID id){
        return clienteService.deletarCliente(id);
    }
}
