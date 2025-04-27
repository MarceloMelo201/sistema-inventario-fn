package com.bytenest.sistema_inventario_fn.controllers;

import com.bytenest.sistema_inventario_fn.dtos.UsuarioDto;
import com.bytenest.sistema_inventario_fn.model.UserModel;
import com.bytenest.sistema_inventario_fn.repositories.UsuarioRepository;
import com.bytenest.sistema_inventario_fn.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
        return userService.salvarUsuario(usuarioDto);
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UserModel>> listarTodasOsUsuarios(){
        return userService.listarTodasOsUsuarios();
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Object> listarUmUsuario(@PathVariable(value = "id") Long id){
        return userService.listarUsuario(id);
    }

    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Object> atualizarUsuario(@PathVariable (value = "id") Long id,
                                                @RequestBody @Valid UsuarioDto usuarioDto) {
        return userService.atualizarUsuario(id, usuarioDto);
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Object> deletarUsuario(@PathVariable (value = "id") Long id){
        return userService.deletarUsuario(id);
    }
}
