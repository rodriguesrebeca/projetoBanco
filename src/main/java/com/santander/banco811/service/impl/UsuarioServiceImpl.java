package com.santander.banco811.service.impl;

import com.santander.banco811.model.Usuario;
import com.santander.banco811.repository.UsuarioRepository;
import com.santander.banco811.service.UsuarioService;
import com.santander.banco811.dto.request.UsuarioRequest;
import com.santander.banco811.dto.response.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Page<Usuario> getAll(String nome, int page, int size) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "nome");

       if (nome != null) {
           return usuarioRepository.findByNome(nome, pageRequest);
       } else {
           return usuarioRepository.findAll(pageRequest);
       }
    }

    @Override
    public Page<UsuarioResponse> getAllByCpf(String cpf, int page, int size){

        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "nome");

        return usuarioRepository.findByCpf(cpf, pageRequest);
    }

    @Override
    public Page<UsuarioResponse> getAllByNomeAndCpf(String nome, String cpf, int page, int size) {
        PageRequest pageRequest = PageRequest.of(
                page,
                size,
                Sort.Direction.ASC,
                "nome");

        return usuarioRepository.findByNomeAndCpf(nome, cpf, pageRequest);
    }

    @Override
    public UsuarioResponse create(UsuarioRequest usuarioRequest) {
        Usuario usuario = new Usuario(usuarioRequest);
        usuarioRepository.save(usuario);
        return new UsuarioResponse(usuario);
    }

    @Override
    public Usuario getById(Integer id) {
        return usuarioRepository.findById(id).orElseThrow();
    }

    @Override
    public Usuario update(UsuarioRequest usuarioRequest, Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow();
        usuario.setNome(usuarioRequest.getNome());
        usuario.setCpf(usuarioRequest.getCpf());
        usuario.setSenha(usuarioRequest.getSenha());

        return usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Integer id) {
        var usuario = usuarioRepository.findById(id).orElseThrow();
        usuarioRepository.delete(usuario);
    }

//    @Override
//    public List<UsuarioResponse> getAllByDataCriacao(LocalDateTime dataCriação) {
//            return usuarioRepository.findByDataCriacaoAfter(dataCriação);
//    }


}
