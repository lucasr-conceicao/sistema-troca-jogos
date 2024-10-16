package br.com.jogos.service;

import br.com.jogos.domain.TabelaMensagem;
import br.com.jogos.dto.MensagemDTORequest;
import br.com.jogos.dto.MensagemDTOResponse;
import br.com.jogos.repository.MensagemRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MensagemService {

    private final MensagemRepository mensagemRepository;
    private final PropostaTrocaService trocaService;
    private final UsuarioService usuarioService;

    @Transactional
    public MensagemDTOResponse enviarMensagem(MensagemDTORequest request) {
        var troca = trocaService.recuperarProposta(request.trocaId());
        var usuario = usuarioService.recuperarUsuario(request.usuarioId());

        var mensagemEntity = TabelaMensagem.builder()
                .id(UUID.randomUUID())
                .conteudo(request.conteudo())
                .dataEnvioMensagem(LocalDateTime.now())
                .trade(troca)
                .usuario(usuario)
                .build();

        var mensagem = mensagemRepository.save(mensagemEntity);
        return converterEntityEmDTO(mensagem);
    }

    @Transactional
    public List<MensagemDTOResponse> listarMensagensPorTroca(UUID tradeId) {
        List<TabelaMensagem> mensagens = mensagemRepository.findByTradeId(tradeId);
        return mensagens.stream().map(this::converterEntityEmDTO).collect(Collectors.toList());
    }

    private MensagemDTOResponse converterEntityEmDTO(TabelaMensagem mensagem) {
        return MensagemDTOResponse.builder()
                .id(mensagem.getId())
                .conteudo(mensagem.getConteudo())
                .dataEnvio(mensagem.getDataEnvioMensagem())
                .trocaId(mensagem.getTrade().getId())
                .usuarioId(mensagem.getUsuario().getId())
                .build();
    }
}

