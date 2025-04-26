package com.bytenest.sistema_inventario_fn.controllers;

import com.bytenest.sistema_inventario_fn.dtos.FuncionarioDto;
import com.bytenest.sistema_inventario_fn.model.FuncionarioModel;
import com.bytenest.sistema_inventario_fn.repositories.FuncionarioRepository;
import com.bytenest.sistema_inventario_fn.services.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
public class FuncionarioController {

    @Autowired
    FuncionarioRepository funcionarioRepository;

    @Autowired
    FuncionarioService funcionarioService;

    @PostMapping("/funcionarios")
    public ResponseEntity<?> salvarFuncionario(@RequestBody @Valid FuncionarioDto funcionarioDto) {
        return funcionarioService.salvarFuncionario(funcionarioDto);
    }

    @GetMapping("/funcionarios")
    public ResponseEntity<List<FuncionarioModel>> listarTodasOsFuncionarios(){
        return funcionarioService.listarTodasOsFuncionarios();
    }

    @GetMapping("/funcionarios/{id}")
    public ResponseEntity<Object> listarFuncionario(@PathVariable(value = "id") UUID id){
        return funcionarioService.listarFuncionario(id);
    }

    @PutMapping("/funcionarios/{id}")
    public ResponseEntity<Object> atualizarFuncionario(@PathVariable (value = "id") UUID id,
                                                   @RequestBody @Valid FuncionarioDto funcionarioDto) {
        return funcionarioService.atualizarFuncionario(id, funcionarioDto);
    }

    @DeleteMapping("/funcionarios/{id}")
    public ResponseEntity<Object> deletarFuncionario(@PathVariable (value = "id") UUID id){
        return funcionarioService.deletarFuncionario(id);
    }
}
