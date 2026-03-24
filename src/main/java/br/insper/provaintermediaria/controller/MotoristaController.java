package br.insper.provaintermediaria.controller;

import br.insper.provaintermediaria.model.Motorista;
import br.insper.provaintermediaria.service.MotoristaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/motoristas")
public class MotoristaController {

    @Autowired
    private MotoristaService motoristaService;

    @PostMapping
    public Motorista criar(@RequestBody Motorista motorista) {
        return motoristaService.criar(motorista);
    }

    @GetMapping
    public List<Motorista> listar() {
        return motoristaService.listar();
    }

    @GetMapping("/{id}")
    public Motorista buscar(@PathVariable Long id) {
        return motoristaService.buscarAtivo(id);
    }

    @PutMapping("/{id}")
    public Motorista atualizar(@PathVariable Long id, @RequestBody Motorista motorista) {
        return motoristaService.atualizar(id, motorista);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        motoristaService.excluir(id);
    }
}