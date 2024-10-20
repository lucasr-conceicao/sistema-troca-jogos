package br.com.jogos.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@Data
@Entity(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_usuario")
@EqualsAndHashCode(of = "id")
public class TabelaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true)
    private String username;

    private String password;

    private String nome;

    @Column(unique = true)
    private String email;

    @Column(name = "preferencia_de_troca")
    private String preferenciaDeTroca;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<TabelaJogos> jogos;
}