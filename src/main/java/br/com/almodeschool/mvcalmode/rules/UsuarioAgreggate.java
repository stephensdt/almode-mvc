package br.com.almodeschool.mvcalmode.rules;

import br.com.almodeschool.mvcalmode.exception.ParamsNotValidException;
import br.com.almodeschool.mvcalmode.exception.UserNotFoundException;
import br.com.almodeschool.mvcalmode.models.Usuario;
import br.com.almodeschool.mvcalmode.repositories.UsuarioRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Component
@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = PRIVATE)
public class UsuarioAgreggate {
    UsuarioRepository usuarioRepository;

    public Page<Usuario> findAll(Pageable pageable) {
        return this.usuarioRepository
                .findAll(pageable);
    }

    @Transactional
    public Usuario create(CreateUsuarioParams usuario) {
        var usuarioNovo = new Usuario(usuario.getNome(), usuario.getCpf());
        return this.usuarioRepository.save(usuarioNovo);
    }

    @Transactional
    public Usuario update(UpdateUsuarioParams updateUsuario, UUID idDoUsuario) {
        var usuario = this.usuarioRepository.findById(idDoUsuario)
                .orElseThrow(UserNotFoundException::new);
        usuario.setNome(updateUsuario.getNome());
        return this.usuarioRepository.save(usuario);
    }

    @Transactional
    public void deleteUsuario(UUID idDoUsuario) {
        this.usuarioRepository.findById(idDoUsuario).ifPresent(this.usuarioRepository::delete);
    }

    public Usuario getUsuarioById(UUID idDoUsuario) {
        return this.usuarioRepository
                .findById(idDoUsuario)
                .orElseThrow(UserNotFoundException::new);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @FieldDefaults(level = PRIVATE)
    public  static class CreateUsuarioParams {
        @NotBlank(message = "não pode ser branco")
        @NotNull(message = "não pode ser nulo")
        String nome;

        @NotBlank(message = "não pode ser branco")
        @NotNull(message = "não pode ser nulo")
        String cpf;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @FieldDefaults(level = PRIVATE)
    public  static class UpdateUsuarioParams {
        @NotBlank(message = "não pode ser branco")
        @NotNull(message = "não pode ser nulo")
        String nome;
    }
}
