package br.com.jogos.service;

import br.com.jogos.domain.TabelaJogos;
import br.com.jogos.domain.TabelaPropostaTroca;
import br.com.jogos.domain.TabelaUsuario;
import br.com.jogos.dto.PropostaTrocaDTORequest;
import br.com.jogos.dto.PropostaTrocaDTOResponse;
import br.com.jogos.enums.StatusPropostaTroca;
import br.com.jogos.exception.NotFoundException;
import br.com.jogos.repository.PropostaTrocaRepository;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
class PropostaTrocaServiceTest {

    @Mock
    private PropostaTrocaRepository propostaTrocaRepository;

    @Mock
    private JogosService jogosService;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private PropostaTrocaService propostaTrocaService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveCadastrarPropostaComSucesso() {

        when(jogosService.recuperarJogo(any(UUID.class))).thenReturn(getResponseTabelaJogos());
        when(usuarioService.recuperarUsuario(any(UUID.class))).thenReturn(getResponseTabelaUsuario());
        when(propostaTrocaRepository.save(any())).thenReturn(getResponseTabelaTroca());

        PropostaTrocaDTOResponse response = propostaTrocaService.cadastrarProposta(propostaTrocaDTORequest());

        verify(propostaTrocaRepository, times(1)).save(any());
    }

    @Test
    void deveBuscarPropostasComSucesso() {
        val usuarioId = UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec");

        when(usuarioService.recuperarUsuario(usuarioId)).thenReturn(getResponseTabelaUsuario());
        when(propostaTrocaRepository.findByUsuarioOfertante(any())).thenReturn(getResponseListTabelaTroca());

        List<PropostaTrocaDTOResponse> propostas = propostaTrocaService.buscarPropostas(usuarioId);

        assertEquals(1, propostas.size());
    }

    @Test
    void deveLancarExceptionAoBuscarPropostaInexistente() {
        val propostaId = UUID.randomUUID();
        when(propostaTrocaRepository.findById(propostaId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> propostaTrocaService.buscarPropostaTrocaPorId(propostaId));
    }

//    @Test
//    void deveResponderPropostaAceitaComSucesso() {
//        val propostaId = UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec");
//        val usuarioId = UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec");
//
//        when(usuarioService.recuperarUsuario(usuarioId)).thenReturn(getResponseTabelaUsuario());
//        when(propostaTrocaRepository.findById(propostaId)).thenReturn(Optional.of(getResponseTabelaTroca()));
//        when(propostaTrocaRepository.save(getResponseTabelaTroca())).thenReturn(getResponseTabelaTroca());
//
//        propostaTrocaService.responderPropostaAceita(propostaId, usuarioId);
//
//        verify(propostaTrocaRepository, times(1)).save(getResponseTabelaTroca());
//    }
//
//    @Test
//    void deveExcluirPropostaComSucesso() {
//        val propostaId = UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec");
//
//        val proposta = new TabelaPropostaTroca();
//
//        when(propostaTrocaRepository.findById(propostaId)).thenReturn(Optional.of(proposta));
//
//        propostaTrocaService.excluirProposta(propostaId);
//
//        verify(propostaTrocaRepository, times(1)).deleteById(propostaId);
//    }

    private TabelaPropostaTroca getResponseTabelaTroca() {
        return TabelaPropostaTroca.builder()
                .id(UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec"))
                .statusTroca(StatusPropostaTroca.ACEITA)
                .dataResposta(LocalDateTime.parse("2024-10-20T03:47:35.864468"))
                .dataCriacao(LocalDateTime.parse("2024-10-20T03:47:35.864468"))
                .usuarioOfertante(getResponseTabelaUsuario())
                .usuarioDestinatario(getResponseTabelaUsuario())
                .jogoDesejado(getResponseTabelaJogos())
                .jogoOferecido(getResponseTabelaJogos())
                .build();
    }

    private List<TabelaPropostaTroca> getResponseListTabelaTroca() {
        return List.of(
                TabelaPropostaTroca.builder()
                        .id(UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec"))
                        .statusTroca(StatusPropostaTroca.ACEITA)
                        .dataResposta(LocalDateTime.parse("2024-10-20T03:47:35.864468"))
                        .dataCriacao(LocalDateTime.parse("2024-10-20T03:47:35.864468"))
                        .usuarioOfertante(getResponseTabelaUsuario())
                        .usuarioDestinatario(getResponseTabelaUsuario())
                        .jogoDesejado(getResponseTabelaJogos())
                        .jogoOferecido(getResponseTabelaJogos())
                        .build()
        );
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

    private PropostaTrocaDTORequest propostaTrocaDTORequest() {
        return PropostaTrocaDTORequest.builder()
                .jogoDesejado(UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec"))
                .jogoOferecido(UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec"))
                .usuarioDestinatario(UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec"))
                .usuarioOfertante(UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec"))
                .build();
    }
}
