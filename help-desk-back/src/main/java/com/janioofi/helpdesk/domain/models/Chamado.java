package com.janioofi.helpdesk.domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.janioofi.helpdesk.domain.enums.Prioridade;
import com.janioofi.helpdesk.domain.enums.Status;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "tb_chamado")
public class Chamado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_chamado;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;
    @NotNull(message = "Campo Prioridade não pode ser nulo")
//    @NotEmpty(message = "Campo Prioridade não pode estar vazio")
    private Prioridade prioridade;
    @NotNull(message = "Campo Status não pode ser nulo")
    private Status status;
    @NotNull(message = "Campo Titulo não pode ser nulo")
    private String titulo;
    @NotNull(message = "Campo Observações não pode ser nulo")
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "id_tecnico")
    @NotNull(message = "Campo Tecnico não pode ser nulo")
//    @NotEmpty(message = "Campo Tecnico não pode estar vazio")
    private Tecnico tecnico;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    @NotNull(message = "Campo Cliente não pode ser nulo")
    private Cliente cliente;

    public Chamado() {
    }

    public Chamado(Integer id_chamado, Prioridade prioridade, Status status, String titulo, String observacoes, Tecnico tecnico, Cliente cliente) {
        this.id_chamado = id_chamado;
        this.prioridade = prioridade;
        this.status = status;
        this.titulo = titulo;
        this.observacoes = observacoes;
        this.tecnico = tecnico;
        this.cliente = cliente;
    }

    public Integer getId_chamado() {
        return id_chamado;
    }

    public void setId_chamado(Integer id_chamado) {
        this.id_chamado = id_chamado;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chamado chamado = (Chamado) o;
        return Objects.equals(id_chamado, chamado.id_chamado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id_chamado);
    }
}
