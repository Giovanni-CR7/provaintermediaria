package br.insper.provaintermediaria.model;

import java.time.LocalDate;

public class Corrida {
    private Long id;
    private LocalDate dataCorrida;
    private Usuario usuario;
    private Motorista motorista;


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Motorista getMotorista() { return motorista; }
    public void setMotorista(Motorista motorista) { this.motorista = motorista; }

    public LocalDate getDataCorrida() { return dataCorrida; }
    public void setDataCorrida(LocalDate dataCorrida) { this.dataCorrida = dataCorrida; }

}