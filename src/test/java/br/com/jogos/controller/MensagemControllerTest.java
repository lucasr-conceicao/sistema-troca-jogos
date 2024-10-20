package br.com.jogos.controller;

import br.com.jogos.dto.MensagemDTORequest;
import br.com.jogos.dto.MensagemDTOResponse;
import br.com.jogos.service.MensagemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class MensagemControllerTest {

    @Mock
    private MensagemService mensagemService;

    @InjectMocks
    private MensagemController mensagemController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveEnviarMensagemComSucesso() {
        UUID trocaId = UUID.randomUUID();
        UUID usuarioId = UUID.randomUUID();
        String conteudo = "Mensagem de teste";
        MensagemDTORequest request = new MensagemDTORequest(trocaId, usuarioId, conteudo);

        MensagemDTOResponse mensagemResponse = new MensagemDTOResponse(
                UUID.randomUUID(),
                trocaId,
                usuarioId,
                conteudo,
                LocalDateTime.now()
        );

        when(mensagemService.enviarMensagem(request)).thenReturn(mensagemResponse);

        ResponseEntity<MensagemDTOResponse> response = mensagemController.enviarMensagem(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mensagemResponse, response.getBody());
    }

    @Test
    void deveListarMensagensPorTrocaComSucesso() {
        UUID trocaId = UUID.randomUUID();
        MensagemDTOResponse mensagem1 = new MensagemDTOResponse(UUID.randomUUID(), trocaId, UUID.randomUUID(), "Mensagem 1", LocalDateTime.now());
        MensagemDTOResponse mensagem2 = new MensagemDTOResponse(UUID.randomUUID(), trocaId, UUID.randomUUID(), "Mensagem 2", LocalDateTime.now());
        List<MensagemDTOResponse> mensagens = List.of(mensagem1, mensagem2);

        when(mensagemService.listarMensagensPorTroca(trocaId)).thenReturn(mensagens);
        ResponseEntity<List<MensagemDTOResponse>> response = mensagemController.listarMensagensPorTroca(trocaId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mensagens, response.getBody());
    }
}
