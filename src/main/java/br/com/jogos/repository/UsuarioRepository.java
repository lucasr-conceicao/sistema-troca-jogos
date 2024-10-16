package br.com.jogos.repository;

import br.com.jogos.domain.TabelaUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository extends JpaRepository<TabelaUsuario, UUID> {
    TabelaUsuario findByUsername(String username);
}
