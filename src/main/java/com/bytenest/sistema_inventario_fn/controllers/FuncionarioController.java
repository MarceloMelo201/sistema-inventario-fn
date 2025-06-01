package com.bytenest.sistema_inventario_fn.controllers;

import com.bytenest.sistema_inventario_fn.dtos.FuncionarioDto;
import com.bytenest.sistema_inventario_fn.model.entities.FuncionarioModel;
import com.bytenest.sistema_inventario_fn.repositories.FuncionarioRepository;
import com.bytenest.sistema_inventario_fn.services.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        try{
            FuncionarioModel funcionario = funcionarioService.salvarFuncionario(funcionarioDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/funcionarios")
    public ResponseEntity<List<FuncionarioModel>> listarTodasOsFuncionarios(){
        List<FuncionarioModel> listFuncionario = funcionarioService.listarTodosOsFuncionarios();
        return ResponseEntity.status(HttpStatus.OK).body(listFuncionario);
    }

    @GetMapping("/funcionarios/{id}")
    public ResponseEntity<Object> listarFuncionario(@PathVariable(value = "id") UUID id){
        try {
            FuncionarioModel funcionario = funcionarioService.listarFuncionario(id);
            return ResponseEntity.status(HttpStatus.OK).body(funcionario);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/funcionarios/{id}")
    public ResponseEntity<Object> atualizarFuncionario(@PathVariable (value = "id") UUID id,
                                                   @RequestBody @Valid FuncionarioDto funcionarioDto) {
        try {
            FuncionarioModel funcionario = funcionarioService.atualizarFuncionario(id, funcionarioDto);
            return ResponseEntity.status(HttpStatus.OK).body(funcionario);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/funcionarios/{id}")
    public ResponseEntity<Object> deletarFuncionario(@PathVariable (value = "id") UUID id){
        try {
            funcionarioService.deletarFuncionario(id);
            return ResponseEntity.status(HttpStatus.OK).body("Funcionário deletado com sucesso.");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
