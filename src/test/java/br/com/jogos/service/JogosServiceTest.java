package br.com.jogos.service;

import br.com.jogos.domain.TabelaJogos;
import br.com.jogos.domain.TabelaUsuario;
import br.com.jogos.dto.JogosDTORequest;
import br.com.jogos.dto.JogosDTOResponse;
import br.com.jogos.repository.JogosRepository;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
class JogosServiceTest {

    @Mock
    private JogosRepository jogosRepository;

    @Mock
    private UsuarioService usuarioService;

    @InjectMocks
    private JogosService jogosService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveBuscarJogoComSucesso() {
        val jogoId = UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec");

        when(jogosRepository.findById(jogoId)).thenReturn(Optional.ofNullable(getResponseTabelaJogos()));

        JogosDTOResponse response = jogosService.buscarPorId(jogoId);

        assertEquals("F1 2024", response.titulo());
        assertEquals("Formula 1", response.descricao());
    }

    @Test
    void deveExcluirJogoComSucesso() {
        val jogoId = UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec");

        when(jogosRepository.findById(jogoId)).thenReturn(Optional.of(getResponseTabelaJogos()));

        jogosService.excluirJogo(jogoId);

        verify(jogosRepository, times(1)).deleteById(jogoId);
    }

    @Test
    void deveAtualizarJogoComSucesso() {
        val jogoId = UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec");
        val jogoExistente = getResponseTabelaJogos();
        val jogoDTO = jogosDTORequestAtualizado();

        when(jogosRepository.findById(jogoId)).thenReturn(Optional.of(jogoExistente));

        jogosService.atualizarJogo(jogoId, jogoDTO);

        assertEquals("F1 2024 Atualizado", jogoExistente.getTitulo());
        assertEquals("Formula 1 Atualizada", jogoExistente.getDescricao());
        Mockito.verify(jogosRepository, times(1)).save(jogoExistente);
    }

    @Test
    void deveBuscarJogosComSucesso() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 10);
        TabelaJogos jogo1 = getResponseTabelaJogos();
        TabelaJogos jogo2 = getResponseTabelaJogos();

        val pageJogos = new PageImpl<>(List.of(jogo1, jogo2), pageable, 2);

        when(jogosRepository.findAll(pageable)).thenReturn(pageJogos);

        val response = jogosService.buscarJogos(pageable);

        assertEquals(2, response.getTotalElements());
        assertEquals("F1 2024", response.getContent().get(0).titulo());
    }

    private TabelaJogos getResponseTabelaJogos() {
        return TabelaJogos.builder()
                .id(UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec"))
                .titulo("F1 2024")
                .descricao("Formula 1")
                .usuario(getResponseTabelaUsuario())
                .dataCadastro(LocalDateTime.now())
                .build();
    }

    private TabelaUsuario getResponseTabelaUsuario() {
        return TabelaUsuario.builder()
                .id(UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec"))
                .email("lucas@hotmail.com")
                .username("lucasfiap")
                .build();
    }

    private JogosDTORequest jogosDTORequestAtualizado() {
        return JogosDTORequest.builder()
                .titulo("F1 2024 Atualizado")
                .descricao("Formula 1 Atualizada")
                .build();
    }
}
