package br.insper.provaintermediaria.service;

import br.insper.provaintermediaria.model.Motorista;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Random;

@Service
public class MotoristaService {

    private final HashMap<Long, Motorista> motoristas = new HashMap<>();
    private Long sequence = 1L;

    public Motorista criar(Motorista motorista) {
        validarCamposObrigatorios(motorista);

        motorista.setId(sequence++);
        motorista.setAtivo(true);
        motoristas.put(motorista.getId(), motorista);
        return motorista;
    }

    public List<Motorista> listar() {
        return motoristas.values().stream()
                .filter(Motorista::isAtivo)
                .toList();
    }

    public Motorista buscarAtivo(Long id) {
        Motorista motorista = motoristas.get(id);
        if (motorista == null || !motorista.isAtivo()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Motorista não encontrado");
        }
        return motorista;
    }

    public Motorista atualizar(Long id, Motorista novo) {
        Motorista atual = buscarAtivo(id);

        atual.setNome(novo.getNome());
        atual.setCpf(novo.getCpf());
        atual.setPlacaCarro(novo.getPlacaCarro());
        atual.setEmail(novo.getEmail());

        validarCamposObrigatorios(atual);

        return atual;
    }

    public void excluir(Long id) {
        Motorista motorista = buscarAtivo(id);
        motorista.setAtivo(false);
    }

    private void validarCamposObrigatorios(Motorista motorista) {
        if (motorista.getNome() == null || motorista.getNome().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nome é obrigatório");
        }
        if (motorista.getCpf() == null || motorista.getCpf().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "CPF é obrigatório");
        }
        if (motorista.getPlacaCarro() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Número da placa é obrigatório");
        }
        if (motorista.getEmail() == null || motorista.getEmail().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "E-mail é obrigatório");
        }
    }


    public Motorista buscarAleatorio() {
        List<Motorista> ativos = motoristas.values().stream()
                .filter(Motorista::isAtivo)
                .toList();

        if (ativos.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Nenhum motorista disponível");
        }

        Random random = new Random();
        return ativos.get(random.nextInt(ativos.size()));
    }

}