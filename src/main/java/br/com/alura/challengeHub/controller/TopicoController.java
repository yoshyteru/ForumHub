package br.com.alura.challengeHub.controller;

import br.com.alura.challengeHub.domain.curso.CursoRepository;
import br.com.alura.challengeHub.domain.topico.*;
import br.com.alura.challengeHub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder) {
        // Verificar duplicidade
        if (topicoRepository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            return ResponseEntity.badRequest().body("Tópico duplicado: título e mensagem já existem");
        }

        var autor = usuarioRepository.findById(dados.autorId())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        var curso = cursoRepository.findById(dados.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        var topico = new Topico(dados.titulo(), dados.mensagem(), (br.com.alura.challengeHub.domain.usuario.Usuario) autor, curso);
        topicoRepository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(
            @PageableDefault(size = 10, sort = "dataCriacao") Pageable paginacao,
            @RequestParam(required = false) String nomeCurso,
            @RequestParam(required = false) Integer ano) {

        Page<Topico> page;

        if (nomeCurso != null && ano != null) {
            page = topicoRepository.findByCursoNomeAndAno(nomeCurso, ano, paginacao);
        } else {
            page = topicoRepository.findAll(paginacao);
        }

        return ResponseEntity.ok(page.map(DadosListagemTopico::new));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var topico = topicoRepository.findById(id);

        if (topico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico.get()));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizacaoTopico dados) {
        var optionalTopico = topicoRepository.findById(id);

        if (optionalTopico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var topico = optionalTopico.get();
        topico.atualizar(dados.titulo(), dados.mensagem());

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var optionalTopico = topicoRepository.findById(id);

        if (optionalTopico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        topicoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}