package com.example.demo.repository;

import com.example.demo.model.Usuario;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repo;  // ← aquí sí existe el bean

    @Test
    @DisplayName("existsByEmail devuelve true si el email está guardado")
    void testExistsByEmail() {
        Usuario u = new Usuario("test@mail.com", "rock");
        repo.save(u);

        boolean existe = repo.existsByEmail("test@mail.com");
        assertThat(existe).isTrue();
    }
}
