package br.com.jogos.service;

import br.com.jogos.domain.TabelaUsuario;
import br.com.jogos.dto.AutenticarDTORequest;
import br.com.jogos.dto.UsuarioDTORequest;
import br.com.jogos.dto.UsuarioDTOResponse;
import br.com.jogos.exception.NotFoundException;
import br.com.jogos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Transactional
    public String logarUsuario(AutenticarDTORequest autenticarDTORequest) {
        var usuario = usuarioRepository.findByUsername(autenticarDTORequest.username());
        if (usuario != null) {
            if (Objects.equals(usuario.getPassword(), autenticarDTORequest.senha()) && Objects.equals(usuario.getUsername(), autenticarDTORequest.username())) {
                return "Usuário autenticado com sucesso.";
            }
        }
        throw new NotFoundException("Usuário não autenticado");
    }

    @Transactional
    public UsuarioDTOResponse cadastrarUsuario(UsuarioDTORequest usuarioDTO) {
        var usuarioEntity = converterDTOEmEntity(usuarioDTO);
        var usuario = usuarioRepository.save(usuarioEntity);
        return converterEntityEmDTO(usuario);
    }

    @Transactional
    public TabelaUsuario recuperarUsuario(UUID usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NotFoundException("Usuario não encontrado"));
    }

    @Transactional
    public UsuarioDTOResponse buscarUsuarioPorId(UUID usuarioId) {
        var usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new NotFoundException("Usuario não encontrado"));
        return converterEntityEmDTO(usuario);
    }

    @Transactional
    public void excluirUsuario(UUID usuarioId) {
        var usuario = this.recuperarUsuario(usuarioId);
        usuarioRepository.deleteById(usuario.getId());
    }

    @Transactional
    public UsuarioDTOResponse atualizarUsuario(UUID usuarioId, UsuarioDTORequest usuarioDTO) {
        var usuario = this.recuperarUsuario(usuarioId);
        updateEntity(usuario, usuarioDTO);
        usuarioRepository.save(usuario);
        return converterEntityEmDTO(usuario);
    }

    private void updateEntity(TabelaUsuario usuario, UsuarioDTORequest usuarioDTO) {
        usuario.setNome(usuarioDTO.nome());
        usuario.setUsername(usuarioDTO.username());
        usuario.setEmail(usuarioDTO.email());
        usuario.setPassword(usuarioDTO.senha());
        usuario.setPreferenciaDeTroca(usuarioDTO.preferenciaDeTroca());
    }

    private TabelaUsuario converterDTOEmEntity(UsuarioDTORequest usuarioDTO) {
        return TabelaUsuario.builder()
                .id(UUID.randomUUID())
                .password(usuarioDTO.senha())
                .nome(usuarioDTO.nome())
                .email(usuarioDTO.email())
                .username(usuarioDTO.username())
                .preferenciaDeTroca(usuarioDTO.preferenciaDeTroca())
                .build();
    }


    private UsuarioDTOResponse converterEntityEmDTO(TabelaUsuario usuario) {
        return UsuarioDTOResponse.builder()
                .id(UUID.randomUUID())
                .nome(usuario.getNome())
                .senha(usuario.getPassword())
                .email(usuario.getEmail())
                .username(usuario.getUsername())
                .preferenciaDeTroca(usuario.getPreferenciaDeTroca())
                .build();
    }
}
