package br.insper.provaintermediaria.service;

import br.insper.provaintermediaria.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;

@Service
public class UsuarioService {

    private final HashMap<Long, Usuario> usuarios = new HashMap<>();
    private Long sequence = 1L;

    public Usuario criar(Usuario usuario) {
        validarCamposObrigatorios(usuario);

        usuario.setId(sequence++);
        usuario.setAtivo(true);
        usuarios.put(usuario.getId(), usuario);
        return usuario;
    }

    public List<Usuario> listar() {
        return usuarios.values().stream()
                .filter(Usuario::isAtivo)
                .toList();
    }

    public Usuario buscarAtivo(Long id) {
        Usuario usuario = usuarios.get(id);
        if (usuario == null || !usuario.isAtivo()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuario não encontrado");
        }
        return usuario;
    }

    public Usuario atualizar(Long id, Usuario novo) {
        Usuario atual = buscarAtivo(id);

        atual.setNome(novo.getNome());
        atual.setCpf(novo.getCpf());
        atual.setDataNascimento(novo.getDataNascimento());
        atual.setEmail(novo.getEmail());

        validarCamposObrigatorios(atual);

        return atual;
    }

    public void excluir(Long id) {
        Usuario usuario = buscarAtivo(id);
        usuario.setAtivo(false);
    }

    private void validarCamposObrigatorios(Usuario usuario) {
        if (usuario.getNome() == null || usuario.getNome().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome é obrigatório");
        }
        if (usuario.getCpf() == null || usuario.getCpf().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF é obrigatório");
        }
        if (usuario.getDataNascimento() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de nascimento é obrigatória");
        }
        if (usuario.getEmail() == null || usuario.getEmail().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail é obrigatório");
        }
    }

}