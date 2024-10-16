package br.com.jogos.repository;

import br.com.jogos.domain.TabelaPropostaTroca;
import br.com.jogos.domain.TabelaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PropostaTrocaRepository extends JpaRepository<TabelaPropostaTroca, UUID> {

    List<TabelaPropostaTroca> findByUsuarioOfertante(TabelaUsuario usuarioOfertante);
}
