package br.com.jogos.controller;

import br.com.jogos.dto.JogosDTORequest;
import br.com.jogos.dto.JogosDTOResponse;
import br.com.jogos.handler.JsonHandler;
import br.com.jogos.service.JogosService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static br.com.jogos.utils.PayloadMensagemSwaggerApi.jogoNaoEncontrado;
import static br.com.jogos.utils.PayloadMensagemSwaggerApi.usuarioNaoEncontrado;

@RestController
@RequestMapping("/api-jogos/v1")
@Tag(name = "jogos", description = "API para gerenciamento de Jogos")
@RequiredArgsConstructor
public class JogosController {

    private final JogosService jogosService;

    @GetMapping("/listar/jogos")
    @Operation(summary = "Listar todos os jogos", description = "Listar todos os jogos", tags = "jogos", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = JogosDTOResponse.class))),
            @ApiResponse(responseCode = "404", description = "jogo não encontrado", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = JsonHandler.class),
                    examples = @ExampleObject(value = jogoNaoEncontrado)))
    })
    public ResponseEntity<Page<JogosDTOResponse>> listarTodos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<JogosDTOResponse> jogos = jogosService.buscarJogos(pageable);
        return ResponseEntity.ok(jogos);
    }

    @GetMapping("buscar/{jogoId}")
    @Operation(summary = "Buscar jogo por id", description = "Buscar jogo por id", tags = "jogos", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "jogo não encontrado", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = JsonHandler.class),
                    examples = @ExampleObject(value = usuarioNaoEncontrado)))
    })
    public ResponseEntity<JogosDTOResponse> buscarPorId(@PathVariable UUID jogoId) {
        JogosDTOResponse jogo = jogosService.buscarPorId(jogoId);
        return ResponseEntity.ok(jogo);
    }

    @PostMapping("/cadastrar")
    @Operation(summary = "Cadastrar jogo", description = "Cadastrar jogo", tags = "jogos", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = JsonHandler.class),
                    examples = @ExampleObject(value = usuarioNaoEncontrado)))
    })
    public ResponseEntity<JogosDTOResponse> cadastrarJogo(@Valid @RequestBody JogosDTORequest jogosDTO) {
        JogosDTOResponse jogo = jogosService.cadastrarJogo(jogosDTO);
        return new ResponseEntity<>(jogo, HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/{jogoId}")
    @Operation(summary = "Atualizar jogo", description = "Atualizar jogo por id", tags = "jogos")
    public ResponseEntity<?> atualizarJogo(@PathVariable UUID jogoId, @Valid @RequestBody JogosDTORequest jogosDTO) {
        jogosService.atualizarJogo(jogoId, jogosDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deletar/{jogoId}")
    @Operation(summary = "Excluir jogo", description = "Excluir jogo por id", tags = "jogos")
    public ResponseEntity<Void> excluirJogo(@PathVariable UUID jogoId) {
        jogosService.excluirJogo(jogoId);
        return ResponseEntity.ok().build();
    }
}
