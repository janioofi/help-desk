package com.janioofi.helpdesk.services;

import com.janioofi.helpdesk.domain.dtos.ChamadoDTO;
import com.janioofi.helpdesk.domain.enums.Prioridade;
import com.janioofi.helpdesk.domain.enums.Status;
import com.janioofi.helpdesk.domain.models.Chamado;
import com.janioofi.helpdesk.domain.models.Cliente;
import com.janioofi.helpdesk.domain.models.Tecnico;
import com.janioofi.helpdesk.exceptions.RecordNotFoundException;
import com.janioofi.helpdesk.domain.repositories.ChamadoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChamadoService {
    private final ChamadoRepository repository;
    private final TecnicoService tecnicoService;
    private final ClienteService clienteService;

    public ChamadoService(ChamadoRepository repository, TecnicoService tecnicoService, ClienteService clienteService) {
        this.repository = repository;
        this.tecnicoService = tecnicoService;
        this.clienteService = clienteService;
    }

    public ChamadoDTO findById(Integer id){
        ChamadoDTO chamado =  new ChamadoDTO(repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Nenhum chamado encontrado com o id: " + id)));
        return chamado;
    }

    public List<ChamadoDTO> findAll(){
        List<Chamado> list = repository.findAll();
        return list.stream().map(ChamadoDTO::new).collect(Collectors.toList());
    }

    public Chamado create(ChamadoDTO objDTO) {
        return repository.save(newChamado(objDTO));
    }

    public Chamado update(Integer id, ChamadoDTO objDTO) {
        objDTO.setId_chamado(id);
        Chamado oldObj;
        oldObj = newChamado(objDTO);
        oldObj.setDataAbertura(objDTO.getDataAbertura());
        return repository.save(oldObj);
    }

    private Chamado newChamado(ChamadoDTO obj){
        Tecnico tecnico = tecnicoService.findById(obj.getId_tecnico());
        Cliente cliente = clienteService.findById(obj.getId_cliente());

        Chamado chamado = new Chamado();
        if(obj.getId_chamado() != null){
            chamado.setId_chamado(obj.getId_chamado());
        }

        if(obj.getStatus().equals(Status.ENCERRADO.getDescricao())){
            chamado.setDataFechamento(LocalDate.now());
        }

        chamado.setCliente(cliente);
        chamado.setTecnico(tecnico);
        chamado.setPrioridade(Prioridade.valueOf(obj.getPrioridade()));
        chamado.setStatus(Status.valueOf(obj.getStatus()));
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());
        return chamado;
    }
}
