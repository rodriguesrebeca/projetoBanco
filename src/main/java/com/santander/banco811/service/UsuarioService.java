package com.santander.banco811.service;

import com.santander.banco811.model.Usuario;
import com.santander.banco811.dto.request.UsuarioRequest;
import com.santander.banco811.dto.response.UsuarioResponse;
import org.springframework.data.domain.Page;


public interface UsuarioService {

    Page<Usuario> getAll(String nome, int page, int size);
    Page<UsuarioResponse> getAllByCpf(String cpf, int page, int size);
    Page<UsuarioResponse> getAllByNomeAndCpf(String nome, String cpf, int page, int size);
    UsuarioResponse create(UsuarioRequest usuarioRequest);
    Usuario getById(Integer id);
    Usuario update(UsuarioRequest usuarioRequest, Integer id);
    void delete(Integer id);

//    List<UsuarioResponse> getAllByDataCriacao(LocalDateTime dataCriação);
}
