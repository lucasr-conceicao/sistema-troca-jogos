package br.com.jogos.controller;

import br.com.jogos.dto.PropostaTrocaDTORequest;
import br.com.jogos.dto.PropostaTrocaDTOResponse;
import br.com.jogos.service.PropostaTrocaService;
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

class PropostaTrocaControllerTest {

    @Mock
    private PropostaTrocaService propostaTrocaService;

    @InjectMocks
    private PropostaTrocaController propostaTrocaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveListarPropostasComSucesso() {
        UUID usuarioOfertanteId = UUID.randomUUID();
        PropostaTrocaDTOResponse proposta1 = PropostaTrocaDTOResponse.builder()
                .id(UUID.randomUUID())
                .jogoOferecido(UUID.randomUUID())
                .jogoDesejado(UUID.randomUUID())
                .usuarioDestinatario(UUID.randomUUID())
                .usuarioOfertante(usuarioOfertanteId)
                .status("Pendente")
                .dataCriacao(LocalDateTime.now())
                .build();

        List<PropostaTrocaDTOResponse> propostas = List.of(proposta1);
        when(propostaTrocaService.buscarPropostas(usuarioOfertanteId)).thenReturn(propostas);

        ResponseEntity<List<PropostaTrocaDTOResponse>> response = propostaTrocaController.listarTodas(usuarioOfertanteId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(propostas, response.getBody());
    }

    @Test
    void deveBuscarPropostaPorIdComSucesso() {
        UUID propostaTrocaId = UUID.randomUUID();
        PropostaTrocaDTOResponse proposta = PropostaTrocaDTOResponse.builder()
                .id(propostaTrocaId)
                .jogoOferecido(UUID.randomUUID())
                .jogoDesejado(UUID.randomUUID())
                .usuarioDestinatario(UUID.randomUUID())
                .usuarioOfertante(UUID.randomUUID())
                .status("Aceita")
                .dataCriacao(LocalDateTime.now())
                .build();

        when(propostaTrocaService.buscarPropostaTrocaPorId(propostaTrocaId)).thenReturn(proposta);

        ResponseEntity<PropostaTrocaDTOResponse> response = propostaTrocaController.buscarTroca(propostaTrocaId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(proposta, response.getBody());
    }

    @Test
    void deveCadastrarPropostaComSucesso() {
        PropostaTrocaDTORequest propostaRequest = PropostaTrocaDTORequest.builder()
                .jogoOferecido(UUID.randomUUID())
                .jogoDesejado(UUID.randomUUID())
                .usuarioDestinatario(UUID.randomUUID())
                .usuarioOfertante(UUID.randomUUID())
                .build();

        PropostaTrocaDTOResponse propostaResponse = PropostaTrocaDTOResponse.builder()
                .id(UUID.randomUUID())
                .jogoOferecido(propostaRequest.jogoOferecido())
                .jogoDesejado(propostaRequest.jogoDesejado())
                .usuarioDestinatario(propostaRequest.usuarioDestinatario())
                .usuarioOfertante(propostaRequest.usuarioOfertante())
                .status("Pendente")
                .dataCriacao(LocalDateTime.now())
                .build();

        when(propostaTrocaService.cadastrarProposta(propostaRequest)).thenReturn(propostaResponse);

        ResponseEntity<PropostaTrocaDTOResponse> response = propostaTrocaController.cadastrarProposta(propostaRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(propostaResponse, response.getBody());
    }

    @Test
    void deveResponderPropostaAceitarComSucesso() {
        UUID propostaId = UUID.randomUUID();
        UUID usuarioId = UUID.randomUUID();
        PropostaTrocaDTOResponse propostaResponse = PropostaTrocaDTOResponse.builder()
                .id(propostaId)
                .status("Aceita")
                .build();

        when(propostaTrocaService.responderPropostaAceita(propostaId, usuarioId)).thenReturn(propostaResponse);

        ResponseEntity<PropostaTrocaDTOResponse> response = propostaTrocaController.responderPropostaAceitar(propostaId, usuarioId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(propostaResponse, response.getBody());
    }

    @Test
    void deveExcluirPropostaComSucesso() {
        UUID propostaId = UUID.randomUUID();

        ResponseEntity<Void> response = propostaTrocaController.excluirProposta(propostaId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
