package com.janioofi.helpdesk.domain.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.janioofi.helpdesk.domain.enums.Perfil;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Tecnico extends Pessoa{
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        addPefil(Perfil.TECNICO);
    }

    public Tecnico(Integer id_pessoa, String nome, String cpf, String email, String senha) {
        super(id_pessoa, nome, cpf, email, senha);
        addPefil(Perfil.TECNICO);
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
