package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "usuarios", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "Formato de email inválido")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "El estilo de música es obligatorio")
    @Pattern(regexp = "rock|pop|clasica", flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "El estilo debe ser rock, pop o clasica")
    @JoinColumn(name = "estilo_musica_id", nullable = false)
    private String estiloMusica;

   
    public Usuario() {}

    public Usuario(String email, String estiloMusica) {
        this.email = email;
        this.estiloMusica = estiloMusica;
    }

   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstiloMusica() {
        return estiloMusica;
    }

    public void setEstiloMusica(String estiloMusica) {
        this.estiloMusica = estiloMusica;
    }
}
