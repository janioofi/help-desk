package com.janioofi.helpdesk.domain;

import com.janioofi.helpdesk.domain.enums.Perfil;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class Pessoa {
    protected Integer id_pessoa;
    protected String nome;
    protected String cpf;
    protected String email;
    protected String senha;
    protected Set<Integer> perfis = new HashSet<>();
    protected LocalDate dateCriacao = LocalDate.now();

    public Pessoa() {
        super();
        addPefil(Perfil.CLIENTE);
    }

    public Pessoa(Integer id_pessoa, String nome, String cpf, String email, String senha) {
        this.id_pessoa = id_pessoa;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        addPefil(Perfil.CLIENTE);
    }

    public Integer getId_pessoa() {
        return id_pessoa;
    }

    public void setId_pessoa(Integer id_pessoa) {
        this.id_pessoa = id_pessoa;
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
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPefil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

    public LocalDate getDateCriacao() {
        return dateCriacao;
    }

    public void setDateCriacao(LocalDate dateCriacao) {
        this.dateCriacao = dateCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(id_pessoa, pessoa.id_pessoa) && Objects.equals(cpf, pessoa.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_pessoa, cpf);
    }
}
