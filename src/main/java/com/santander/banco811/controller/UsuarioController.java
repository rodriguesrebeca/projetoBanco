package com.santander.banco811.controller;

import com.santander.banco811.model.Usuario;
import com.santander.banco811.service.UsuarioService;
import com.santander.banco811.dto.request.UsuarioRequest;
import com.santander.banco811.dto.response.UsuarioResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public Page<Usuario> getAll(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false, defaultValue = "0")int page,
            @RequestParam(required = false, defaultValue = "3") int size) {
        return usuarioService.getAll(nome, page, size);
    }

    @GetMapping("/cpf")
    public Page<UsuarioResponse> getAllByCpf(
            @RequestParam String cpf,
            @RequestParam(required = false, defaultValue = "0")int page,
            @RequestParam(required = false, defaultValue = "3") int size){
        return usuarioService.getAllByCpf(cpf, page, size);
    }

    @GetMapping("/nome")
    public Page<UsuarioResponse> getAllByNomeAndCpf(
            @RequestParam String nome,
            @RequestParam String cpf,
            @RequestParam(required = false, defaultValue = "0")int page,
            @RequestParam(required = false, defaultValue = "3") int size){
        return usuarioService.getAllByNomeAndCpf(nome, cpf, page, size);
    }

    @GetMapping("/{id}")
    public Usuario getById(@PathVariable Integer id) {
        return usuarioService.getById(id);
    }

    @PostMapping
    public UsuarioResponse create(@RequestBody UsuarioRequest usuarioRequest) {
        return usuarioService.create(usuarioRequest);
    }

    @PutMapping("/{id}")
    public Usuario update(@PathVariable Integer id, @RequestBody UsuarioRequest usuarioRequest){
        return usuarioService.update(usuarioRequest, id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        usuarioService.delete(id);
    }
}
