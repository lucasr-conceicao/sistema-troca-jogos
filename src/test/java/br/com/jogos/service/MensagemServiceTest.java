package br.com.jogos.service;

import br.com.jogos.domain.TabelaJogos;
import br.com.jogos.domain.TabelaMensagem;
import br.com.jogos.domain.TabelaPropostaTroca;
import br.com.jogos.domain.TabelaUsuario;
import br.com.jogos.dto.MensagemDTORequest;
import br.com.jogos.dto.MensagemDTOResponse;
import br.com.jogos.enums.StatusPropostaTroca;
import br.com.jogos.repository.MensagemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
class MensagemServiceTest {

    @Mock
    private MensagemRepository mensagemRepository;

    @Mock
    private PropostaTrocaService trocaService;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private MensagemService mensagemService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveEnviarMensagemComSucesso() {
        UUID trocaId = UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec");
        UUID usuarioId = UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec");
        MensagemDTORequest request = new MensagemDTORequest( trocaId, usuarioId, "Mensagem Teste");
        var troca = getResponseTabelaTroca();
        var usuario = getResponseTabelaUsuario();

        when(trocaService.recuperarProposta(trocaId)).thenReturn(troca);
        when(usuarioService.recuperarUsuario(usuarioId)).thenReturn(usuario);
        when(mensagemRepository.save(any(TabelaMensagem.class))).thenAnswer(invocation -> invocation.getArgument(0));

        MensagemDTOResponse response = mensagemService.enviarMensagem(request);

        assertEquals(request.conteudo(), response.conteudo());
        assertEquals(usuarioId, response.usuarioId());
        assertEquals(trocaId, response.trocaId());
    }

    @Test
    void deveListarMensagensPorTrocaComSucesso() {
        UUID tradeId = UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec");
        TabelaMensagem mensagem1 = getResponseTabelaMensagem(tradeId);
        TabelaMensagem mensagem2 = getResponseTabelaMensagem(tradeId);

        when(mensagemRepository.findByTradeId(tradeId)).thenReturn(List.of(mensagem1, mensagem2));

        List<MensagemDTOResponse> responses = mensagemService.listarMensagensPorTroca(tradeId);

        assertEquals(2, responses.size());
        assertEquals(mensagem1.getConteudo(), responses.get(0).conteudo());
        assertEquals(mensagem2.getConteudo(), responses.get(1).conteudo());
    }

    private TabelaMensagem getResponseTabelaMensagem(UUID tradeId) {
        return TabelaMensagem.builder()
                .id(UUID.randomUUID())
                .conteudo("Conteúdo da Mensagem")
                .dataEnvioMensagem(LocalDateTime.now())
                .trade(getResponseTabelaTroca())
                .usuario(getResponseTabelaUsuario())
                .build();
    }

    private TabelaPropostaTroca getResponseTabelaTroca() {
        return TabelaPropostaTroca.builder()
                .id(UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec"))
                .statusTroca(StatusPropostaTroca.PENDENTE)
                .dataResposta(LocalDateTime.parse("2024-10-20T03:47:35.864468"))
                .dataCriacao(LocalDateTime.parse("2024-10-20T03:47:35.864468"))
                .usuarioOfertante(getResponseTabelaUsuario())
                .usuarioDestinatario(getResponseTabelaUsuario())
                .jogoDesejado(getResponseTabelaJogos())
                .jogoOferecido(getResponseTabelaJogos())
                .build();
    }

    private TabelaJogos getResponseTabelaJogos() {
        return TabelaJogos.builder()
                .id(UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec"))
                .titulo("F1 2024")
                .descricao("Formula 1")
                .usuario(getResponseTabelaUsuario())
                .build();
    }

    private TabelaUsuario getResponseTabelaUsuario() {
        return TabelaUsuario.builder()
                .id(UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec"))
                .email("lucas@hotmail.com")
                .username("lucasfiap")
                .build();
    }
}
