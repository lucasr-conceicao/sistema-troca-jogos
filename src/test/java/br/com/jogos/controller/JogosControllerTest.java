package br.com.jogos.controller;

import br.com.jogos.dto.JogosDTORequest;
import br.com.jogos.dto.JogosDTOResponse;
import br.com.jogos.service.JogosService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class JogosControllerTest {

    @Mock
    private JogosService jogosService;

    @InjectMocks
    private JogosController jogosController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveListarTodosOsJogosComSucesso() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        JogosDTOResponse jogo1 = new JogosDTOResponse(UUID.randomUUID(), "Jogo 1", "Descrição 1", UUID.randomUUID(), LocalDateTime.now());
        JogosDTOResponse jogo2 = new JogosDTOResponse(UUID.randomUUID(), "Jogo 2", "Descrição 2", UUID.randomUUID(), LocalDateTime.now());
        Page<JogosDTOResponse> jogosPage = new PageImpl<>(List.of(jogo1, jogo2), pageable, 2);

        when(jogosService.buscarJogos(pageable)).thenReturn(jogosPage);

        ResponseEntity<Page<JogosDTOResponse>> response = jogosController.listarTodos(0, 10);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jogosPage, response.getBody());
    }

    @Test
    void deveBuscarJogoPorIdComSucesso() {
        UUID jogoId = UUID.randomUUID();
        JogosDTOResponse jogo = new JogosDTOResponse(jogoId, "Jogo 1", "Descrição 1", UUID.randomUUID(), LocalDateTime.now());

        when(jogosService.buscarPorId(jogoId)).thenReturn(jogo);

        ResponseEntity<JogosDTOResponse> response = jogosController.buscarPorId(jogoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(jogo, response.getBody());
    }

    @Test
    void deveCadastrarJogoComSucesso() {
        JogosDTORequest jogoRequest = new JogosDTORequest("Jogo 1", "Descrição 1", UUID.randomUUID());
        JogosDTOResponse jogoResponse = new JogosDTOResponse(UUID.randomUUID(), "Jogo 1", "Descrição 1", UUID.randomUUID(), LocalDateTime.now());

        when(jogosService.cadastrarJogo(jogoRequest)).thenReturn(jogoResponse);

        ResponseEntity<JogosDTOResponse> response = jogosController.cadastrarJogo(jogoRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(jogoResponse, response.getBody());
    }

    @Test
    void deveAtualizarJogoComSucesso() {
        UUID jogoId = UUID.randomUUID();
        JogosDTORequest jogoRequest = new JogosDTORequest("Jogo 1", "Descrição 1", UUID.randomUUID());

        ResponseEntity<?> response = jogosController.atualizarJogo(jogoId, jogoRequest);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void deveExcluirJogoComSucesso() {
        UUID jogoId = UUID.randomUUID();

        ResponseEntity<Void> response = jogosController.excluirJogo(jogoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
