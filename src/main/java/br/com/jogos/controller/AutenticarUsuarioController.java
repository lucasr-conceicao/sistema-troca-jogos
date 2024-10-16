package br.com.jogos.controller;


import br.com.jogos.dto.AutenticarDTORequest;
import br.com.jogos.dto.UsuarioDTORequest;
import br.com.jogos.dto.UsuarioDTOResponse;
import br.com.jogos.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api-autenticar/v1")
@Tag(name = "autenticar", description = "API para gerenciamento de Jogos")
@RequiredArgsConstructor
public class AutenticarUsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/cadastrar")
    @Operation(summary = "Cadastrar", description = "Cadastrar usuario", tags = "autenticar")
    public ResponseEntity<UsuarioDTOResponse> cadastrarMorador(@Valid @RequestBody UsuarioDTORequest usuarioDTO) {
        UsuarioDTOResponse usuario = usuarioService.cadastrarUsuario(usuarioDTO);
        return new ResponseEntity<>(usuario, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(summary = "logar", description = "logar usuario", tags = "autenticar")
    public ResponseEntity<String> login(@RequestBody @Valid AutenticarDTORequest autenticarDTORequest) {
        var response = usuarioService.logarUsuario(autenticarDTORequest);
        return ResponseEntity.ok(response);
    }
}
