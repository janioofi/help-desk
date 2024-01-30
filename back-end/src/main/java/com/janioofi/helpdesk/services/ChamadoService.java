package com.janioofi.helpdesk.services;

import com.janioofi.helpdesk.domain.dtos.ChamadoDTO;
import com.janioofi.helpdesk.domain.models.Chamado;
import com.janioofi.helpdesk.exceptions.RecordNotFoundException;
import com.janioofi.helpdesk.repositories.ChamadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChamadoService {
    private final ChamadoRepository repository;

    public ChamadoService(ChamadoRepository repository) {
        this.repository = repository;
    }

    public ChamadoDTO findById(Integer id){
        Chamado obj = repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Nenhum chamado encontrado com o id: " + id));
        return new ChamadoDTO(obj);
    }

    public List<ChamadoDTO> findAll(){
        List<Chamado> list = repository.findAll();
        List<ChamadoDTO> dtos = list.stream().map(ChamadoDTO::new).collect(Collectors.toList());
        return dtos;
    }
}
