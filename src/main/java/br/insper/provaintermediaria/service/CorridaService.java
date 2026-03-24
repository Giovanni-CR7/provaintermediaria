package br.insper.provaintermediaria.service;

import br.insper.provaintermediaria.model.Corrida;
import br.insper.provaintermediaria.model.Motorista;
import br.insper.provaintermediaria.model.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;

@Service
public class CorridaService {

    private final UsuarioService usuarioService;
    private final MotoristaService motoristaService;

    public CorridaService(UsuarioService usuarioService, MotoristaService motoristaService) {
        this.usuarioService = usuarioService;
        this.motoristaService = motoristaService;
    }
    private final HashMap<Long, Corrida> corridas = new HashMap<>();
    private Long sequence = 1L;

    public Corrida criar(Corrida corrida, long idUsuario) {
        Usuario usuario = usuarioService.buscarAtivo(idUsuario);
        Motorista motorista = motoristaService.buscarAleatorio();

        corrida.setUsuario(usuario);
        corrida.setMotorista(motorista);

        validarCamposObrigatorios(corrida);

        corrida.setId(sequence++);
        corridas.put(corrida.getId(), corrida);

        return corrida;
    }

    public List<Corrida> listar() {
        return corridas.values().stream()
                .toList();
    }

    public Corrida buscarAtivo(Long id) {
        Corrida corrida = corridas.get(id);
        if (corrida == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Corrida não encontrada");
        }
        return corrida;
    }

    public Corrida atualizar(Long id, Corrida novo) {
        Corrida atual = buscarAtivo(id);

        atual.setUsuario(novo.getUsuario());
        atual.setMotorista(novo.getMotorista());
        atual.setDataCorrida(novo.getDataCorrida());

        validarCamposObrigatorios(atual);

        return atual;
    }

    public void excluir(Long id) {
        Corrida corrida = buscarAtivo(id);
    }

    private void validarCamposObrigatorios(Corrida corrida) {
        if (corrida.getUsuario() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuario da corrida é obrigatório");
        }
        if (corrida.getMotorista() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Motorista da corrida é obrigatório");
        }
        if (corrida.getDataCorrida() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data da corrida é obrigatória");
        }
    }

}