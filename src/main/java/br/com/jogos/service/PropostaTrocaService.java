package br.com.jogos.service;

import br.com.jogos.domain.TabelaPropostaTroca;
import br.com.jogos.dto.PropostaTrocaDTORequest;
import br.com.jogos.dto.PropostaTrocaDTOResponse;
import br.com.jogos.enums.StatusPropostaTroca;
import br.com.jogos.exception.NotFoundException;
import br.com.jogos.repository.PropostaTrocaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static br.com.jogos.enums.StatusPropostaTroca.PENDENTE;

@Service
@RequiredArgsConstructor
public class PropostaTrocaService {

    private final PropostaTrocaRepository propostaTrocaRepository;
    private final JogosService jogosService;
    private final UsuarioService usuarioService;

    @Transactional
    public PropostaTrocaDTOResponse cadastrarProposta(PropostaTrocaDTORequest propostaTrocaDTO) {
        var propostaEntity = converterDTOEmEntity(propostaTrocaDTO);
        var proposta = propostaTrocaRepository.save(propostaEntity);
        return converterEntityEmDTO(proposta);
    }

    @Transactional
    public List<PropostaTrocaDTOResponse> buscarPropostas(UUID usuaarioOfertanteId) {
        var usuario = usuarioService.recuperarUsuario(usuaarioOfertanteId);
        var propostas = propostaTrocaRepository.findByUsuarioOfertante(usuario);
        return propostas.isEmpty() ? Collections.emptyList() : propostas.stream().map(this::converterEntityEmDTO).toList();
    }

    @Transactional
    public PropostaTrocaDTOResponse buscarPropostaTrocaPorId(UUID propostaTrocaId) {
        var propostaTroca = propostaTrocaRepository.findById(propostaTrocaId)
                .orElseThrow(() -> new NotFoundException("Proposta de troca não encontrado"));
        return converterEntityEmDTO(propostaTroca);
    }

    @Transactional
    public PropostaTrocaDTOResponse responderPropostaAceita(UUID propostaId, UUID usuarioId) {
        usuarioService.recuperarUsuario(usuarioId);
        var proposta = this.recuperarProposta(propostaId);
        proposta.setStatusTroca(StatusPropostaTroca.ACEITA);
        proposta.setDataResposta(LocalDateTime.now());
        propostaTrocaRepository.save(proposta);
        return converterEntityEmDTO(proposta);
    }

    @Transactional
    public PropostaTrocaDTOResponse responderPropostaRecusada(UUID propostaId, UUID usuarioId) {
        usuarioService.recuperarUsuario(usuarioId);
        var proposta = this.recuperarProposta(propostaId);
        proposta.setStatusTroca(StatusPropostaTroca.REJEITADA);
        proposta.setDataResposta(LocalDateTime.now());
        propostaTrocaRepository.save(proposta);
        return converterEntityEmDTO(proposta);
    }


    @Transactional
    public void excluirProposta(UUID propostaId) {
        var proposta = this.recuperarProposta(propostaId);

        propostaTrocaRepository.deleteById(proposta.getId());
    }

    @Transactional
    public TabelaPropostaTroca recuperarProposta(UUID propostaId) {
        return propostaTrocaRepository.findById(propostaId)
                .orElseThrow(() -> new NotFoundException("Proposta de troca não encontrada"));
    }

    private TabelaPropostaTroca converterDTOEmEntity(PropostaTrocaDTORequest propostaTrocaDTO) {
        var jogoOferecido = jogosService.recuperarJogo(propostaTrocaDTO.jogoOferecido());
        var jogoDesejado = jogosService.recuperarJogo(propostaTrocaDTO.jogoDesejado());
        var usuarioDestinatario = usuarioService.recuperarUsuario(propostaTrocaDTO.usuarioDestinatario());
        var usuarioOfertante = usuarioService.recuperarUsuario(propostaTrocaDTO.usuarioOfertante());

        return TabelaPropostaTroca.builder()
                .id(UUID.randomUUID())
                .jogoOferecido(jogoOferecido)
                .jogoDesejado(jogoDesejado)
                .usuarioDestinatario(usuarioDestinatario)
                .usuarioOfertante(usuarioOfertante)
                .statusTroca(PENDENTE)
                .dataCriacao(LocalDateTime.now())
                .build();
    }

    private PropostaTrocaDTOResponse converterEntityEmDTO(TabelaPropostaTroca proposta) {
        return PropostaTrocaDTOResponse.builder()
                .id(proposta.getId())
                .jogoOferecido(proposta.getJogoOferecido().getId())
                .jogoDesejado(proposta.getJogoDesejado().getId())
                .usuarioDestinatario(proposta.getUsuarioDestinatario().getId())
                .usuarioOfertante(proposta.getUsuarioOfertante().getId())
                .status(String.valueOf(proposta.getStatusTroca()))
                .dataCriacao(proposta.getDataCriacao())
                .dataResposta(proposta.getDataResposta())
                .build();
    }
}

