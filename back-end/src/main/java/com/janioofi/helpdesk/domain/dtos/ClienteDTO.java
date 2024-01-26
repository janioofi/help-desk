package com.janioofi.helpdesk.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.janioofi.helpdesk.domain.enums.Perfil;
import com.janioofi.helpdesk.domain.models.Cliente;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ClienteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    protected String nome;
    protected String cpf;
    protected String email;
    protected String senha;
    protected Set<Perfil> perfis = new HashSet<>();
    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dateCriacao = LocalDate.now();

    public ClienteDTO() {
        super();
        this.perfis = new HashSet<>(Perfil.CLIENTE.getCodigo());
    }

    public ClienteDTO(Cliente obj) {
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis = obj.getPerfis();
        this.perfis = new HashSet<>(Perfil.CLIENTE.getCodigo());
        this.dateCriacao = obj.getDateCriacao();
    }

    public ClienteDTO(String nome, String cpf, String email, String senha, Set<Perfil> perfis, LocalDate dateCriacao) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.perfis = perfis;
        this.dateCriacao = dateCriacao;
        this.perfis = new HashSet<>(Perfil.CLIENTE.getCodigo());
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Perfil> getPerfis() {
        return perfis;
    }

    public void setPerfis(Set<Perfil> perfis) {
        this.perfis = perfis;
    }

    public LocalDate getDateCriacao() {
        return dateCriacao;
    }

    public void setDateCriacao(LocalDate dateCriacao) {
        this.dateCriacao = dateCriacao;
    }
}
