package com.janioofi.helpdesk.services;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.janioofi.helpdesk.domain.models.Chamado;
import com.janioofi.helpdesk.domain.models.Cliente;
import com.janioofi.helpdesk.domain.models.Tecnico;
import com.janioofi.helpdesk.domain.enums.Perfil;
import com.janioofi.helpdesk.domain.enums.Prioridade;
import com.janioofi.helpdesk.domain.enums.Status;
import com.janioofi.helpdesk.domain.repositories.ChamadoRepository;
import com.janioofi.helpdesk.domain.repositories.ClienteRepository;
import com.janioofi.helpdesk.domain.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

@Service
public class DBService {
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;
    @Autowired
    private BCryptPasswordEncoder encoder;

    public void instanciaDB(){
        Tecnico tecnico = new Tecnico(null,"Jonas Silveira", "13664666240", "jonas@gmail.com", encoder.encode("teste"));
        Tecnico tecnico1 = new Tecnico(null,"Jonas Silveira", "11188301721", "jonas1@gmail.com", encoder.encode("teste"));
        Tecnico tecnico2 = new Tecnico(null,"Jonas Silveira", "48660357736", "jonas2@gmail.com", encoder.encode("teste"));
        Tecnico tecnico3 = new Tecnico(null,"Jonas Silveira", "67213600800", "jonas3@gmail.com", encoder.encode("teste"));
        Tecnico tecnico4 = new Tecnico(null,"Jonas Silveira", "21621471578", "jonas4@gmail.com", encoder.encode("teste"));
        Tecnico tecnico5 = new Tecnico(null,"Jonas Silveira", "36776612420", "jonas5@gmail.com", encoder.encode("teste"));
        Tecnico tecnico6 = new Tecnico(null,"Jonas Silveira", "64547511802", "jonas6@gmail.com", encoder.encode("teste"));
        Tecnico tecnico7 = new Tecnico(null,"Jonas Silveira", "95257482860", "jonas7@gmail.com", encoder.encode("teste"));
        tecnico.addPerfil(Perfil.ADMIN);
        tecnico1.addPerfil(Perfil.ADMIN);
        tecnico2.addPerfil(Perfil.ADMIN);
        tecnico3.addPerfil(Perfil.ADMIN);
        tecnico4.addPerfil(Perfil.ADMIN);
        tecnico5.addPerfil(Perfil.ADMIN);
        tecnico6.addPerfil(Perfil.ADMIN);
        tecnico7.addPerfil(Perfil.ADMIN);

        Cliente cliente = new Cliente(null, "Rubens Nascimento", "63185578147", "rubens@gmail.com", encoder.encode("teste"));

        Chamado c1 = new Chamado();
        c1.setCliente(cliente);
        c1.setTecnico(tecnico);
        c1.setPrioridade(Prioridade.MEDIA);
        c1.setTitulo("Ajustar Impressora");
        c1.setStatus(Status.ANDAMENTO);
        c1.setDataAbertura(LocalDate.now());
        c1.setObservacoes("Testar");
        tecnicoRepository.save(tecnico);
        tecnicoRepository.save(tecnico1);
        tecnicoRepository.save(tecnico2);
        tecnicoRepository.save(tecnico3);
        tecnicoRepository.save(tecnico4);
        tecnicoRepository.save(tecnico5);
        tecnicoRepository.save(tecnico6);
        tecnicoRepository.save(tecnico7);
        clienteRepository.save(cliente);
        chamadoRepository.save(c1);
    }
}
