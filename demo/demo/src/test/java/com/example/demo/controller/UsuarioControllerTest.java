package com.example.demo.controller;

import com.example.demo.Controller.UsuarioController;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UsuarioRepository repo;  // ← aquí se crea un mock bean

    @Test
    void crearUsuario_OK() throws Exception {
        given(repo.existsByEmail("u@d.com")).willReturn(false);
        given(repo.save(any(Usuario.class))).willReturn(new Usuario("u@d.com", "rock"));

        String json = """
          {"email":"u@d.com","estiloMusica":"rock"}
        """;

        mvc.perform(post("/api/usuarios")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Su Encuesta se ingresó correctamente"))
                .andExpect(jsonPath("$.codigo").value("OK"));
    }
}
