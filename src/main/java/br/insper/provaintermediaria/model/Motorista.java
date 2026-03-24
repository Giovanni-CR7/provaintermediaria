package br.insper.provaintermediaria.model;

import java.time.LocalDate;

public class Motorista {
    private Long id;
    private String nome;
    private String cpf;
    private String placaCarro;
    private String email;
    private boolean ativo = true;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getPlacaCarro() { return placaCarro; }
    public void setPlacaCarro(String placaCarro) { this.placaCarro = placaCarro; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isAtivo() { return ativo; }
    public void setAtivo(boolean ativo) { this.ativo = ativo; }
}