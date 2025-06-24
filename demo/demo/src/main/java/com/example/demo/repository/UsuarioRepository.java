package com.example.demo.repository;

import com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    boolean existsByEmail(String email);

    @Query("SELECT u.estiloMusica, COUNT(u) FROM Usuario u GROUP BY u.estiloMusica ORDER BY COUNT(u) DESC")
    List<Object[]> contarVotosPorEstilo();
}


