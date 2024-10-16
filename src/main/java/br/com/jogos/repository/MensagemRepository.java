package br.com.jogos.repository;

import br.com.jogos.domain.TabelaMensagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MensagemRepository extends JpaRepository<TabelaMensagem, UUID> {
    List<TabelaMensagem> findByTradeId(UUID tradeId);
}
