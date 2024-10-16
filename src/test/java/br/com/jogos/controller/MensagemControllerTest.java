package br.com.jogos.controller;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import br.com.jogos.dto.MensagemDTORequest;
import br.com.jogos.dto.MensagemDTOResponse;
import br.com.jogos.service.MensagemService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.mockito.Mockito.when;

public class MensagemControllerTest {

    @InjectMocks
    private MensagemController mensagemController;

    @Mock
    private MensagemService mensagemService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testEnviarMensagem() {
        MensagemDTORequest request = new MensagemDTORequest(any(), any(), any());
        MensagemDTOResponse response = new MensagemDTOResponse(any(), any(), any(), any(), any());

        when(mensagemService.enviarMensagem(any(MensagemDTORequest.class))).thenReturn(response);

        ResponseEntity<MensagemDTOResponse> result = mensagemController.enviarMensagem(request);

        assertEquals(200, result.getStatusCodeValue());
        assertEquals(response, result.getBody());

        ArgumentCaptor<MensagemDTORequest> captor = ArgumentCaptor.forClass(MensagemDTORequest.class);
        verify(mensagemService).enviarMensagem(captor.capture());
        assertEquals(request, captor.getValue());
    }

    @Test
    public void testListarMensagensPorTroca() {
        UUID tradeId = UUID.randomUUID();
        List<MensagemDTOResponse> responseList = List.of(new MensagemDTOResponse(any(), any(), any(), any(), any()));

        when(mensagemService.listarMensagensPorTroca(tradeId)).thenReturn(responseList);

        ResponseEntity<List<MensagemDTOResponse>> result = mensagemController.listarMensagensPorTroca(tradeId);

        assertEquals(responseList.get(0).trocaId(), result.getBody().get(0).trocaId());

        verify(mensagemService).listarMensagensPorTroca(tradeId);
    }
}
