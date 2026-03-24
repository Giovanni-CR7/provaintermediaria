package br.insper.provaintermediaria.controller;

import br.insper.provaintermediaria.model.Corrida;
import br.insper.provaintermediaria.model.Motorista;
import br.insper.provaintermediaria.model.Usuario;
import br.insper.provaintermediaria.service.CorridaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/corridas")
public class CorridaController {

    @Autowired
    private CorridaService corridaService;

    @PostMapping
    public Corrida criar(@RequestBody Corrida corrida, @RequestHeader("ID-USUARIO") long idUsuario) {
        return corridaService.criar(corrida,idUsuario);
    }

    @GetMapping
    public List<Corrida> listar() {
        return corridaService.listar();
    }

    @GetMapping("/{id}")
    public Corrida buscar(@PathVariable Long id) {
        return corridaService.buscarAtivo(id);
    }

    @PutMapping("/{id}")
    public Corrida atualizar(@PathVariable Long id, @RequestBody Corrida corrida) {
        return corridaService.atualizar(id, corrida);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        corridaService.excluir(id);
    }
}