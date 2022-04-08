package com.santander.banco811.repository;

import com.santander.banco811.model.Usuario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;

@DataJpaTest
@Profile("test")
public class UsuarioRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    public void validar_findAll_vazio_se_repository_em_branco() {
        var usuarios = usuarioRepository.findAll();

        Assertions.assertEquals(Arrays.asList(), usuarios);
    }

    @Test
    public void salvar_um_novo_usuario_com_sucesso() {
        Usuario usuario = new Usuario();
        usuario.setCpf("12345678911");
        usuario.setNome("Teste");
        usuario.setSenha("12345678");

        usuario = usuarioRepository.save(usuario);

        Assertions.assertNotNull(usuario.getId());
        Assertions.assertNotNull(usuario.getDataAtualizacao());
        Assertions.assertNotNull(usuario.getDataCriacao());
    }

    @Test
    public void trazer_usuarios_cadastrados_no_find_all() {
        Usuario usuario = new Usuario();
        usuario.setNome("Maria");
        usuario.setSenha("12345677");
        usuario.setCpf("12312312312");

        Usuario usuario2 = new Usuario();
        usuario2.setNome("Anderson");
        usuario2.setSenha("12345677");
        usuario2.setCpf("12312312313");

        entityManager.persist(usuario);
        entityManager.persist(usuario2);

        var usuarios = usuarioRepository.findAll();

        Assertions.assertEquals(Arrays.asList(usuario, usuario2), usuarios);
    }

    @Test
    public void trazer_usuario_cadastrados_pelo_cpf() {
        Usuario usuario = new Usuario();
        usuario.setNome("Maria");
        usuario.setSenha("12345677");
        usuario.setCpf("12312312312");

        Usuario usuario2 = new Usuario();
        usuario2.setCpf("12345678912");
        usuario2.setNome("Laura");
        usuario2.setSenha("12345678");

        PageRequest pageRequest = PageRequest.of(
                0,
                3,
                Sort.Direction.DESC,
                "nome");

        usuario = entityManager.persist(usuario);
        usuario2 = entityManager.persist(usuario2);

    var usuarioEncontrado = usuarioRepository.findByCpf(usuario.getCpf(), pageRequest);

        Assertions.assertEquals(1, usuarioEncontrado.getTotalElements());
        Assertions.assertEquals(usuario, usuarioEncontrado.stream().findFirst().get());

    }

    @Test
    public void encontrar_usuario_pelo_id() {
        Usuario usuario = new Usuario();
        usuario.setCpf("12345678911");
        usuario.setNome("Maria");
        usuario.setSenha("12345678");

        usuario = entityManager.persist(usuario);


        var usuarioEncontrado = usuarioRepository.findById(usuario.getId()).get();

        Assertions.assertEquals(usuarioEncontrado.getId(), usuario.getId());
        Assertions.assertEquals(usuarioEncontrado.getCpf(), usuario.getCpf());
    }


}
