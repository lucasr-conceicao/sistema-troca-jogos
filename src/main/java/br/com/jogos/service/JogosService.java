package br.com.jogos.service;

import br.com.jogos.domain.TabelaJogos;
import br.com.jogos.dto.JogosDTORequest;
import br.com.jogos.dto.JogosDTOResponse;
import br.com.jogos.exception.NotFoundException;
import br.com.jogos.repository.JogosRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JogosService {

    private final JogosRepository jogosRepository;
    private final UsuarioService usuarioService;

    @Transactional
    public JogosDTOResponse cadastrarJogo(JogosDTORequest jogosDTO) {
        var jogoEntity = converterDTOEmEntity(jogosDTO);
        var jogo = jogosRepository.save(jogoEntity);
        return converterEntityEmDTO(jogo);
    }

    @Transactional
    public JogosDTOResponse buscarPorId(UUID jogoId) {
        var jogo = jogosRepository.findById(jogoId)
                .orElseThrow(() -> new NotFoundException("Jogo não encontrado"));
        return converterEntityEmDTO(jogo);
    }

    @Transactional
    public Page<JogosDTOResponse> buscarJogos(Pageable pageable) {
        var jogos = jogosRepository.findAll(pageable);
        return jogos.map(this::converterEntityEmDTO);
    }

    @Transactional
    public void excluirJogo(UUID jogoId) {
        var jogo = this.recuperarJogo(jogoId);
        jogosRepository.deleteById(jogo.getId());
    }

    @Transactional
    public void atualizarJogo(UUID jogoId, JogosDTORequest jogoDTO) {
        var jogo = this.recuperarJogo(jogoId);
        updateEntity(jogo, jogoDTO);
        jogosRepository.save(jogo);
    }

    @Transactional
    public TabelaJogos recuperarJogo(UUID jogoId) {
        return jogosRepository.findById(jogoId)
                .orElseThrow(() -> new NotFoundException("Jogo não encontrado"));
    }


    private void updateEntity(TabelaJogos jogo, JogosDTORequest jogoDTO) {
        jogo.setDescricao(jogoDTO.descricao());
        jogo.setTitulo(jogoDTO.titulo());
    }


    private TabelaJogos converterDTOEmEntity(JogosDTORequest jogoDTO) {
        var usuario = usuarioService.recuperarUsuario(jogoDTO.usuario());
        return TabelaJogos.builder()
                .id(UUID.randomUUID())
                .dataCadastro(LocalDateTime.now())
                .usuario(usuario)
                .descricao(jogoDTO.descricao())
                .titulo(jogoDTO.titulo())
                .build();
    }

    private JogosDTOResponse converterEntityEmDTO(TabelaJogos jogo) {
        return JogosDTOResponse.builder()
                .id(jogo.getId())
                .titulo(jogo.getTitulo())
                .descricao(jogo.getDescricao())
                .usuarioId(jogo.getUsuario().getId())
                .dataCadastro(jogo.getDataCadastro())
                .build();
    }
}