package br.com.jogos.repository;


import br.com.jogos.domain.TabelaJogos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JogosRepository extends JpaRepository<TabelaJogos, UUID> {

}
