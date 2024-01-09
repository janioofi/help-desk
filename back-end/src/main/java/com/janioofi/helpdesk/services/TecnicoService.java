package com.janioofi.helpdesk.services;

import com.janioofi.helpdesk.domain.models.Tecnico;
import com.janioofi.helpdesk.exceptions.RecordNotFoundException;
import com.janioofi.helpdesk.repositories.TecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoService {
    private final TecnicoRepository repository;

    public TecnicoService(TecnicoRepository repository) {
        this.repository = repository;
    }

    public Tecnico findById(Integer id){
        return repository.findById(id).orElseThrow(() -> {
            throw new RecordNotFoundException("Nenhum tecnico encontrado com o id: " + id);
        });
    }

    public List<Tecnico> findAll(){
        return repository.findAll();
    }
}
