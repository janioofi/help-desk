package com.janioofi.helpdesk.services;

import com.janioofi.helpdesk.domain.models.Chamado;
import com.janioofi.helpdesk.domain.models.Cliente;
import com.janioofi.helpdesk.domain.models.Tecnico;
import com.janioofi.helpdesk.domain.enums.Perfil;
import com.janioofi.helpdesk.domain.enums.Prioridade;
import com.janioofi.helpdesk.domain.enums.Status;
import com.janioofi.helpdesk.repositories.ChamadoRepository;
import com.janioofi.helpdesk.repositories.ClienteRepository;
import com.janioofi.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DBService {
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB(){
        Tecnico tecnico = new Tecnico(null,"Jona Silveira", "66232536100", "jonas@gmail.com", "teste");
        tecnico.addPerfil(Perfil.ADMIN);

        Cliente cliente = new Cliente(null, "Rubens Nascimento", "63185578147", "rubens@gmail.com", "teste");

        Chamado c1 = new Chamado();
        c1.setCliente(cliente);
        c1.setTecnico(tecnico);
        c1.setPrioridade(Prioridade.MEDIA);
        c1.setTitulo("Ajustar Impressora");
        c1.setStatus(Status.ANDAMENTO);
        c1.setDataAbertura(LocalDate.now());
        c1.setObservacoes("Testar");
        tecnicoRepository.save(tecnico);
        clienteRepository.save(cliente);
        chamadoRepository.save(c1);
    }
}
