package br.com.jogos.controller;

import br.com.jogos.dto.JogosDTOResponse;
import br.com.jogos.dto.MensagemDTORequest;
import br.com.jogos.dto.MensagemDTOResponse;
import br.com.jogos.handler.JsonHandler;
import br.com.jogos.service.MensagemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static br.com.jogos.utils.PayloadMensagemSwaggerApi.usuarioNaoEncontrado;

@RestController
@RequestMapping("/api/mensagem")
@Tag(name = "mensagem", description = "API para gerenciamento de mensagens")
@RequiredArgsConstructor
public class MensagemController {

    private final MensagemService mensagemService;

    @PostMapping("/enviar")
    @Operation(summary = "Enviar mensagem", description = "Enviar mensagem", tags = "mensagem", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = JogosDTOResponse.class))),
            @ApiResponse(responseCode = "404", description = "jogo não encontrado", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = JsonHandler.class),
                    examples = @ExampleObject(value = usuarioNaoEncontrado)))
    })
    public ResponseEntity<MensagemDTOResponse> enviarMensagem(@RequestBody MensagemDTORequest request) {
        var response = mensagemService.enviarMensagem(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{trocaId}")
    public ResponseEntity<List<MensagemDTOResponse>> listarMensagensPorTroca(@PathVariable UUID trocaId) {
        List<MensagemDTOResponse> responses = mensagemService.listarMensagensPorTroca(trocaId);
        return ResponseEntity.ok(responses);
    }
}