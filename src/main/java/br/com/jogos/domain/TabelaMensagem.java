package br.com.jogos.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_mensagens")
public class TabelaMensagem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String conteudo;

    @Column(name = "data_envio_mensagem")
    private LocalDateTime dataEnvioMensagem;

    @ManyToOne
    @JoinColumn(name = "trade_id")
    private TabelaPropostaTroca trade;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private TabelaUsuario usuario;
}
