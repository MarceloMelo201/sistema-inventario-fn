package com.bytenest.sistema_inventario_fn.controllers;

import com.bytenest.sistema_inventario_fn.dtos.PecaDto;
import com.bytenest.sistema_inventario_fn.model.entities.PecaModel;
import com.bytenest.sistema_inventario_fn.repositories.PecaRepository;
import com.bytenest.sistema_inventario_fn.services.PecaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class PecaController {

    @Autowired
    PecaRepository pecaRepository;

    @Autowired
    PecaService pecaService;

    @PostMapping("/pecas")
    public ResponseEntity<?> salvarPeca(@RequestBody @Valid PecaDto pecaDto){
        try {
            PecaModel peca = pecaService.salvarPeca(pecaDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(peca);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(STR."Erro ao tentar criar peça: \{e.getMessage()}");
        }
    }

    @GetMapping("/pecas")
    public ResponseEntity<List<PecaModel>> listarTodasAsPecas(){
        List<PecaModel> listPeca = pecaService.listarTodasAsPecas();
        return ResponseEntity.status(HttpStatus.OK).body(listPeca);
    }

    @GetMapping("/pecas/{id}")
    public ResponseEntity<Object> listarUmaPeca(@PathVariable(value = "id")UUID id){
        try {
            PecaModel peca = pecaService.listarPeca(id);
            return ResponseEntity.status(HttpStatus.OK).body(peca);
         }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/pecas/{sku}")
    public ResponseEntity<Object> listarUmaPecaPorSku(@PathVariable(value = "id")String sku){
        try {
            PecaModel peca = pecaService.listarPecaSku(sku);
            return ResponseEntity.status(HttpStatus.OK).body(peca);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/pecas/{id}")
    public ResponseEntity<Object> atualizarPeca(@PathVariable(value = "id") UUID id,
                                                @RequestBody @Valid PecaDto pecaDto){
        try {
            PecaModel peca = pecaService.atualizarPeca(id, pecaDto);
            return ResponseEntity.status(HttpStatus.OK).body(peca);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/pecas/{id}")
    public ResponseEntity<Object> deletarPeca(@PathVariable (value = "id") UUID id){
        try {
            pecaService.deletarPeca(id);
            return ResponseEntity.status(HttpStatus.OK).body("Peça deletada com sucesso.");
        }
        catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }



}
