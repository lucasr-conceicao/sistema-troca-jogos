package br.com.jogos.controller;

import br.com.jogos.dto.UsuarioDTORequest;
import br.com.jogos.dto.UsuarioDTOResponse;
import br.com.jogos.handler.JsonHandler;
import br.com.jogos.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api-usuarios/v1")
@Tag(name = "usuarios", description = "API para gerenciamento de Usuários")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/buscar/{usuarioId}")
    @Operation(summary = "Buscar usuário por id", description = "Buscar usuário por id", tags = "usuarios", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = JsonHandler.class),
                    examples = @ExampleObject(value = "usuário não encontrado")))
    })
    public ResponseEntity<UsuarioDTOResponse> buscarPorId(@PathVariable UUID usuarioId) {
        UsuarioDTOResponse usuario = usuarioService.buscarUsuarioPorId(usuarioId);
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/atualizar/{usuarioId}")
    @Operation(summary = "Atualizar usuário", description = "Atualizar usuário por id", tags = "usuarios")
    public ResponseEntity<Void> atualizarUsuario(@PathVariable UUID usuarioId, @Valid @RequestBody UsuarioDTORequest usuarioDTO) {
        usuarioService.atualizarUsuario(usuarioId, usuarioDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deletar/{usuarioId}")
    @Operation(summary = "Excluir usuário", description = "Excluir usuário por id", tags = "usuarios")
    public ResponseEntity<Void> excluirUsuario(@PathVariable UUID usuarioId) {
        usuarioService.excluirUsuario(usuarioId);
        return ResponseEntity.ok().build();
    }
}

