package com.bytenest.sistema_inventario_fn.services;

import com.bytenest.sistema_inventario_fn.dtos.UsuarioDto;
import com.bytenest.sistema_inventario_fn.model.entities.UserModel;
import com.bytenest.sistema_inventario_fn.repositories.ClienteRepository;
import com.bytenest.sistema_inventario_fn.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UsuarioRepository usuarioRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public UserService(UsuarioRepository usuarioRepository,
                       ClienteRepository clienteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.clienteRepository = clienteRepository;
    }

    @Transactional
    public UserModel salvarUsuario(UsuarioDto usuarioDto){
            UserModel user = new UserModel();
            BeanUtils.copyProperties(usuarioDto, user);
            return usuarioRepository.save(user);
    }

    public List<UserModel> listarTodosOsUsuarios(){
        List<UserModel> listUsuario = usuarioRepository.findAll();
        return listUsuario;
    }

    public UserModel listarUsuario(Long id){
        UserModel user = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(STR."Usuário não encontrado com o id: \{id}"));
        return user;
    }

    @Transactional
    public UserModel atualizarUsuario(Long id, UsuarioDto usuarioDto){
        UserModel user = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(STR."Usuário não encontrado com o id: \{id}"));

            BeanUtils.copyProperties(usuarioDto, user);
            return usuarioRepository.save(user);

    }

    @Transactional
    public void deletarUsuario(Long id){
        UserModel user = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(STR."Usuário não encontrado com o id: \{id}"));

        usuarioRepository.delete(user);

    }
}
