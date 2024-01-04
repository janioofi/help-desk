package com.janioofi.helpdesk;

import com.janioofi.helpdesk.domain.Chamado;
import com.janioofi.helpdesk.domain.Cliente;
import com.janioofi.helpdesk.domain.Tecnico;
import com.janioofi.helpdesk.domain.enums.Perfil;
import com.janioofi.helpdesk.domain.enums.Prioridade;
import com.janioofi.helpdesk.domain.enums.Status;
import com.janioofi.helpdesk.repositories.ChamadoRepository;
import com.janioofi.helpdesk.repositories.ClienteRepository;
import com.janioofi.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;

@SpringBootApplication
public class HelpdeskApplication implements CommandLineRunner {
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ChamadoRepository chamadoRepository;

    public static void main(String[] args) {
        SpringApplication.run(HelpdeskApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Tecnico tecnico = new Tecnico(null,"Jona Silveira", "66232536100", "jonas@gmail.com", "teste");
        tecnico.addPefil(Perfil.ADMIN);

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
