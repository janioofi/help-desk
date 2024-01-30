package com.janioofi.helpdesk.domain.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.janioofi.helpdesk.domain.models.Chamado;

import java.time.LocalDate;

public class ChamadoDTO {

    private Integer id_chamado;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;
    private String prioridade;
    private String status;
    private String titulo;
    private String observacoes;
    private String tecnico;
    private String cliente;

    public ChamadoDTO() {
    }

    public ChamadoDTO(Chamado obj) {
        this.id_chamado = obj.getId_chamado();
        this.dataAbertura = obj.getDataAbertura();
        this.dataFechamento = obj.getDataFechamento();
        this.prioridade = obj.getPrioridade().getDescricao();
        this.status = obj.getStatus().getDescricao();
        this.titulo = obj.getTitulo();
        this.observacoes = obj.getObservacoes();
        this.tecnico = obj.getTecnico().getNome();
        this.cliente = obj.getCliente().getNome();
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

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }
}
