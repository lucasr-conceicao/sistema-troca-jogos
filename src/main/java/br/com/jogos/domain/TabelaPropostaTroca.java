package br.com.jogos.domain;

import br.com.jogos.enums.StatusPropostaTroca;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_proposta_troca")
public class TabelaPropostaTroca {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    private StatusPropostaTroca statusTroca;

    @Column(name = "data_criacao")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataCriacao;

    @Column(name = "data_resposta_proposta")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataResposta;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "jogo_oferecido_id")
    private TabelaJogos jogoOferecido;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "jogo_desejado_id")
    private TabelaJogos jogoDesejado;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usuario_ofertante_id")
    private TabelaUsuario usuarioOfertante;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "usuario_destinatario_id")
    private TabelaUsuario usuarioDestinatario;

    @OneToMany(mappedBy = "trade", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TabelaMensagem> mensagens = new ArrayList<>();

}
