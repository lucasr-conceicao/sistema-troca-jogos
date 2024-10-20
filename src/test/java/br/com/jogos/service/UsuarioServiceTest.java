package br.com.jogos.service;

import br.com.jogos.domain.TabelaUsuario;
import br.com.jogos.dto.AutenticarDTORequest;
import br.com.jogos.dto.UsuarioDTORequest;
import br.com.jogos.dto.UsuarioDTOResponse;
import br.com.jogos.exception.NotFoundException;
import br.com.jogos.repository.UsuarioRepository;
import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveLogarUsuarioComSucesso() {
        val usuario = getResponseTabelaUsuario();
        val request = new AutenticarDTORequest("lucasfiap", "senhaCorreta");

        when(usuarioRepository.findByUsername("lucasfiap")).thenReturn(usuario);

        String response = usuarioService.logarUsuario(request);

        assertEquals("Usuário autenticado com sucesso.", response);
    }

    @Test
    void deveLancarNotFoundAoLogarUsuarioInvalido() {
        val request = new AutenticarDTORequest("usuarioInvalido", "senhaErrada");

        when(usuarioRepository.findByUsername("usuarioInvalido")).thenReturn(null);

        assertThrows(NotFoundException.class, () -> usuarioService.logarUsuario(request));
    }

    @Test
    void deveCadastrarUsuarioComSucesso() {
        val usuarioDTO = new UsuarioDTORequest("Lucas", "lucasfiap", "lucas@hotmail.com", "senhaCorreta", null);
        val usuarioEntity = getResponseTabelaUsuario();

        when(usuarioRepository.save(any(TabelaUsuario.class))).thenReturn(usuarioEntity);

        UsuarioDTOResponse response = usuarioService.cadastrarUsuario(usuarioDTO);

        assertEquals("Lucas", response.nome());
        assertEquals("lucasfiap", response.username());
    }

    @Test
    void deveBuscarUsuarioPorIdComSucesso() {
        val usuarioId = UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec");
        val usuario = getResponseTabelaUsuario();

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));

        UsuarioDTOResponse response = usuarioService.buscarUsuarioPorId(usuarioId);

        assertEquals("lucasfiap", response.username());
    }

    @Test
    void deveLancarNotFoundAoBuscarUsuarioPorIdInvalido() {
        val usuarioId = UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec");

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> usuarioService.buscarUsuarioPorId(usuarioId));
    }

    @Test
    void deveExcluirUsuarioComSucesso() {
        val usuarioId = UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec");
        val usuario = getResponseTabelaUsuario();

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuario));

        usuarioService.excluirUsuario(usuarioId);

        verify(usuarioRepository, times(1)).deleteById(usuarioId);
    }

    @Test
    void deveAtualizarUsuarioComSucesso() {
        val usuarioId = UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec");
        val usuarioExistente = getResponseTabelaUsuario();
        val usuarioDTO = new UsuarioDTORequest("Lucas Atualizado", "lucasfiap", "lucas@hotmail.com", "senhaAtualizada", null);

        when(usuarioRepository.findById(usuarioId)).thenReturn(Optional.of(usuarioExistente));
        when(usuarioRepository.save(usuarioExistente)).thenReturn(usuarioExistente);

        UsuarioDTOResponse response = usuarioService.atualizarUsuario(usuarioId, usuarioDTO);

        assertEquals("Lucas Atualizado", response.username());
        Mockito.verify(usuarioRepository, times(1)).save(usuarioExistente);
    }

    private TabelaUsuario getResponseTabelaUsuario() {
        return TabelaUsuario.builder()
                .id(UUID.fromString("f23afb6d-26df-4f12-89b6-18a0674675ec"))
                .email("lucas@hotmail.com")
                .username("lucasfiap")
                .password("senhaCorreta")
                .nome("Lucas")
                .preferenciaDeTroca(null)
                .build();
    }
}
