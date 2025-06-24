package com.example.demo.Controller;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> crearUsuario(@Valid @RequestBody Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            return ResponseEntity.badRequest().body("El email ya está registrado");
        }
        Usuario guardado = usuarioRepository.save(usuario);
        return ResponseEntity.ok(Map.of(
                "mensaje", "Su Encuesta se ingresó correctamente",
                "codigo", "OK"
        ));
    }

    @RestController
    @RequestMapping("/api/estadisticas")
    public class EstadisticasController {

        private final UsuarioRepository usuarioRepository;

        public EstadisticasController(UsuarioRepository usuarioRepository) {
            this.usuarioRepository = usuarioRepository;
        }

        @GetMapping("/votos-musica")
        public List<Map<String, Object>> obtenerVotosPorEstilo() {
            List<Object[]> resultados = usuarioRepository.contarVotosPorEstilo();

            return resultados.stream().map(obj -> {
                Map<String, Object> m = new HashMap<>();
                m.put("estilo", obj[0]);
                m.put("votos", obj[1]);
                return m;
            }).toList();
        }
    }

}
