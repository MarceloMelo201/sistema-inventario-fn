package com.bytenest.sistema_inventario_fn.controllers;

import com.bytenest.sistema_inventario_fn.dtos.ClienteDto;
import com.bytenest.sistema_inventario_fn.model.entities.ClienteModel;
import com.bytenest.sistema_inventario_fn.repositories.ClienteRepository;
import com.bytenest.sistema_inventario_fn.services.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        try {
            ClienteModel cliente = clienteService.salvarCliente(clienteDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(STR."Erro ao salvar cliente: \{e.getMessage()}");
        }
    }

    @GetMapping("/clientes")
    public ResponseEntity<List<ClienteModel>> listarTodasOsClientes(){
        List<ClienteModel> listCliente = clienteService.listarTodosOsClientes();
        return ResponseEntity.status(HttpStatus.OK).body(listCliente);
    }

    @GetMapping("/clientes/{id}")
    public ResponseEntity<Object> listarCliente(@PathVariable(value = "id") UUID id){
        try{
            ClienteModel cliente = clienteService.listarCliente(id);
            return ResponseEntity.status(HttpStatus.OK).body(cliente);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/clientes/{id}")
    public ResponseEntity<Object> atualizarCliente(@PathVariable (value = "id") UUID id,
                                                       @RequestBody @Valid ClienteDto clienteDto) {
        try{
            ClienteModel cliente = clienteService.atualizarCliente(id, clienteDto);
            return ResponseEntity.status(HttpStatus.OK).body(cliente);
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<Object> deletarCliente(@PathVariable (value = "id") UUID id){
        try{
            clienteService.deletarCliente(id);
            return ResponseEntity.status(HttpStatus.OK).body("Cliente excluído com sucesso.");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
