package br.com.alura.challengeHub.domain.topico;

import br.com.alura.challengeHub.domain.curso.Curso;
import br.com.alura.challengeHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensagem;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

    public Topico(String titulo, String mensagem, Usuario autor, Curso curso) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.dataCriacao = LocalDateTime.now();
        this.status = StatusTopico.NAO_RESPONDIDO;
        this.autor = autor;
        this.curso = curso;
    }

    public void atualizar(String titulo, String mensagem) {
        if (titulo != null && !titulo.isBlank()) {
            this.titulo = titulo;
        }
        if (mensagem != null && !mensagem.isBlank()) {
            this.mensagem = mensagem;
        }
    }
}