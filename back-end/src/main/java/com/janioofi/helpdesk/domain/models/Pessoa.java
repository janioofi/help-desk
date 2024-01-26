package com.janioofi.helpdesk.domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.janioofi.helpdesk.domain.enums.Perfil;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "tb_pessoa")
public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id_pessoa;

    @NotNull(message = "Campo NOME não pode ser nulo")
    @NotEmpty(message = "Campo NOME pode estar em branco")
    protected String nome;

    @CPF
    @NotNull(message = "Campo CPF não pode ser nulo")
    @NotEmpty(message = "Campo CPF não pode estar em branco")
    @Column(unique = true)
    protected String cpf;

    @Email
    @NotNull(message = "Campo EMAIL não pode ser nulo")
    @NotEmpty(message = "Campo EMAIL não pode estar em branco")
    @Column(unique = true)
    protected String email;

    @NotNull(message = "Campo SENHA não pode ser nulo")
    @NotEmpty(message = "Campo SENHA não pode estar em branco")
    protected String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    protected Set<Perfil> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dateCriacao = LocalDate.now();

    public Pessoa() {
        super();
        addPerfil(Perfil.CLIENTE);
    }

    public Pessoa(Integer id_pessoa, String nome, String cpf, String email, String senha) {
        this.id_pessoa = id_pessoa;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        addPerfil(Perfil.CLIENTE);
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
        return perfis;
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil);
    }

    public void setPerfis(Set<Perfil> perfis) {
        for(var p : perfis){
            this.perfis.add(p);
        }
        this.perfis = perfis;
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
