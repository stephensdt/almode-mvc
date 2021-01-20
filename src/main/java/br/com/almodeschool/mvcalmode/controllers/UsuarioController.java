package br.com.almodeschool.mvcalmode.controllers;

import br.com.almodeschool.mvcalmode.controllers.utils.UUIDValid;
import br.com.almodeschool.mvcalmode.models.Usuario;
import br.com.almodeschool.mvcalmode.rules.UsuarioAgreggate;
import br.com.almodeschool.mvcalmode.rules.UsuarioAgreggate.CreateUsuarioParams;
import br.com.almodeschool.mvcalmode.rules.UsuarioAgreggate.UpdateUsuarioParams;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@RestController
@RequestMapping("/usuarios")
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class UsuarioController {
    UsuarioAgreggate usuarioAgreggate;

    @GetMapping
    public ResponseEntity<Page<Usuario>> listarUsuarios(@PageableDefault Pageable page) {
        var usuarios  = this
                .usuarioAgreggate
                .findAll(page);
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<Usuario> criaUsuario(@RequestBody @Valid CreateUsuarioParams usuario) {
        var usuarioCriado = this.usuarioAgreggate.create(usuario);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@RequestBody @Valid UpdateUsuarioParams updateUsuario,
                                                 @PathVariable(name = "id") @UUIDValid String id) {
        var usuarioAtualizado = this
                .usuarioAgreggate
                .update(updateUsuario, UUID.fromString(id));
        return ResponseEntity
                .ok(usuarioAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUsuario(@PathVariable @UUIDValid String id) {
        this.usuarioAgreggate
                .deleteUsuario(UUID.fromString(id));
        return ResponseEntity
                .status(HttpStatus.OK).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable @UUIDValid String id) {
            var usuario = this.usuarioAgreggate
                    .getUsuarioById(UUID.fromString(id));
            return ResponseEntity.ok(usuario);
    }
}

