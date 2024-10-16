package br.com.jogos.controller;

import br.com.jogos.dto.PropostaTrocaDTORequest;
import br.com.jogos.dto.PropostaTrocaDTOResponse;
import br.com.jogos.handler.JsonHandler;
import br.com.jogos.service.PropostaTrocaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static br.com.jogos.utils.PayloadMensagemSwaggerApi.*;

@RestController
@RequestMapping("/api-propostas-troca/v1")
@Tag(name = "troca", description = "API para gerenciamento de Propostas de Troca")
@RequiredArgsConstructor
public class PropostaTrocaController {

    private final PropostaTrocaService propostaTrocaService;

    @GetMapping("/listar/usuario_ofertante/{usuarioOfertanteId}")
    @Operation(summary = "Listar todas as propostas ofertadas pelo usuario", description = "Listar todas as propostas de troca", tags = "troca", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PropostaTrocaDTOResponse.class))),
            @ApiResponse(responseCode = "404", description = "Proposta não encontrada", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = JsonHandler.class),
                    examples = @ExampleObject(value = "proposta não encontrada")))
    })
    public ResponseEntity<List<PropostaTrocaDTOResponse>> listarTodas(@PathVariable UUID usuarioOfertanteId) {
        List<PropostaTrocaDTOResponse> propostas = propostaTrocaService.buscarPropostas(usuarioOfertanteId);
        return ResponseEntity.ok(propostas);
    }

    @GetMapping("/buscar/proposta_troca/{propostaTrocaId}")
    @Operation(summary = "Listar todas as propostas ofertadas pelo usuario", description = "Listar todas as propostas de troca", tags = "troca", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PropostaTrocaDTOResponse.class))),
            @ApiResponse(responseCode = "404", description = "Proposta não encontrada", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = JsonHandler.class),
                    examples = @ExampleObject(value = trocarNaoEncontrada)))
    })
    public ResponseEntity<PropostaTrocaDTOResponse> buscarTroca(@PathVariable UUID propostaTrocaId) {
        var proposta = propostaTrocaService.buscarPropostaTrocaPorId(propostaTrocaId);
        return new ResponseEntity<>(proposta, HttpStatus.OK);
    }

    @PostMapping("/cadastrar")
    @Operation(summary = "Cadastrar proposta de troca", description = "Cadastrar proposta de troca", tags = "troca", responses = {
            @ApiResponse(responseCode = "201", description = "Sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PropostaTrocaDTOResponse.class),
                    examples = @ExampleObject(value = cadastroTrocaSucesso))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = JsonHandler.class),
                    examples = @ExampleObject(value = trocarNaoEncontrada)))
    })
    public ResponseEntity<PropostaTrocaDTOResponse> cadastrarProposta(@Valid @RequestBody PropostaTrocaDTORequest propostaTrocaDTO) {
        PropostaTrocaDTOResponse proposta = propostaTrocaService.cadastrarProposta(propostaTrocaDTO);
        return new ResponseEntity<>(proposta, HttpStatus.CREATED);
    }

    @PutMapping("/responder/proposta/{propostaId}/usuario/{usuarioId}/aceitar")
    @Operation(summary = "Responder proposta de troca", description = "Responder proposta de troca", tags = "troca", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PropostaTrocaDTOResponse.class),
                    examples = @ExampleObject(value = trocaAceita))),
            @ApiResponse(responseCode = "404", description = "Dados inválidos", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = JsonHandler.class),
                    examples = @ExampleObject(value = trocarNaoEncontrada)))
    })
    public ResponseEntity<PropostaTrocaDTOResponse> responderPropostaAceitar(@PathVariable UUID propostaId, @PathVariable UUID usuarioId) {
        var proposta = propostaTrocaService.responderPropostaAceita(propostaId, usuarioId);
        return new ResponseEntity<>(proposta, HttpStatus.OK);
    }

    @PutMapping("/responder/proposta/{propostaId}/usuario/{usuarioId}/rejeitar")
    @Operation(summary = "Responder proposta de troca", description = "Responder proposta de troca", tags = "troca", responses = {
            @ApiResponse(responseCode = "200", description = "Sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = PropostaTrocaDTOResponse.class),
                    examples = @ExampleObject(value = trocaRejeitada))),
            @ApiResponse(responseCode = "404", description = "Dados inválidos", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = JsonHandler.class),
                    examples = @ExampleObject(value = jogoNaoEncontrado)))
    })
    public ResponseEntity<PropostaTrocaDTOResponse> responderPropostaRejeitar(@PathVariable UUID propostaId, @PathVariable UUID usuarioId) {
        var proposta = propostaTrocaService.responderPropostaRecusada(propostaId, usuarioId);
        return new ResponseEntity<>(proposta, HttpStatus.OK);
    }

    @DeleteMapping("/deletar/{propostaId}")
    @Operation(summary = "Excluir proposta de troca", description = "Excluir proposta de troca por id", tags = "troca")
    public ResponseEntity<Void> excluirProposta(@PathVariable UUID propostaId) {
        propostaTrocaService.excluirProposta(propostaId);
        return ResponseEntity.ok().build();
    }
}
