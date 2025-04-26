package com.bytenest.sistema_inventario_fn.services;

import com.bytenest.sistema_inventario_fn.dtos.CriarUsuarioDto;
import com.bytenest.sistema_inventario_fn.model.UserModel;
import com.bytenest.sistema_inventario_fn.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class UserService {
    private final UsuarioRepository usuarioRepository;

    @Autowired

    public UserService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public ResponseEntity<?> salvarUsuario(CriarUsuarioDto criarUsuarioDto){
        try {
            UserModel usuarioModel = new UserModel();

            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuarioModel));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    public ResponseEntity<List<UserModel>> listarTodasOsUsuarios(){
        List<UserModel> listUsuario = usuarioRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(listUsuario);
    }

    public ResponseEntity<Object> listarUsuario(Long id){
        Optional<UserModel> usuario0 = usuarioRepository.findById(id);
        return usuario0.<ResponseEntity<Object>> map(UsuarioModel -> ResponseEntity.status(HttpStatus.OK).body(usuario0))
                .orElseGet(() ->ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado."));
    }

    @Transactional
    public ResponseEntity<Object> atualizarUsuario(Long id, CriarUsuarioDto criarUsuarioDto){
        try {
            Optional<UserModel> usuario0 = usuarioRepository.findById(id);

            if(!usuario0.isPresent()){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario não encontrado.");
            }
            var userModel = usuario0.get();
            BeanUtils.copyProperties(criarUsuarioDto, userModel);
            return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(userModel));

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<Object> deletarUsuario(Long id){
        Optional<UserModel> usuario0 = usuarioRepository.findById(id);
        if(usuario0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado.");
        }
        usuarioRepository.delete(usuario0.get());
        return ResponseEntity.status(HttpStatus.OK).body("Usuário deletado com sucesso!");
    }
}
