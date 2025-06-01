package com.bytenest.sistema_inventario_fn.controllers;

import com.bytenest.sistema_inventario_fn.dtos.UsuarioDto;
import com.bytenest.sistema_inventario_fn.model.entities.UserModel;
import com.bytenest.sistema_inventario_fn.repositories.UsuarioRepository;
import com.bytenest.sistema_inventario_fn.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UserService userService;

    @PostMapping("/usuarios")
    public ResponseEntity<?> salvarUsuario(@RequestBody @Valid UsuarioDto usuarioDto) {
        try {
            UserModel user = userService.salvarUsuario(usuarioDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(STR."Erro ao salvar usuário: \{e.getMessage()}");
        }
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UserModel>> listarTodasOsUsuarios(){
        List<UserModel> listUser = userService.listarTodosOsUsuarios();
        return ResponseEntity.status(HttpStatus.OK).body(listUser);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Object> listarUsuario(@PathVariable(value = "id") Long id){
        try {
            UserModel user = userService.listarUsuario(id);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Object> atualizarUsuario(@PathVariable (value = "id") Long id,
                                                @RequestBody @Valid UsuarioDto usuarioDto) {
        try {
            UserModel user = userService.atualizarUsuario(id, usuarioDto);
            return ResponseEntity.status(HttpStatus.OK).body(user);

        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable (value = "id") Long id){
        try {
            userService.deletarUsuario(id);
            return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso.");

        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
