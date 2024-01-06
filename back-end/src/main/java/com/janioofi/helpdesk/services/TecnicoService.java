package com.janioofi.helpdesk.services;

import com.janioofi.helpdesk.domain.Tecnico;
import com.janioofi.helpdesk.repositories.TecnicoRepository;
import org.springframework.stereotype.Service;

@Service
public class TecnicoService {
    private final TecnicoRepository repository;

    public TecnicoService(TecnicoRepository repository) {
        this.repository = repository;
    }

    public Tecnico findById(Integer id){
        return repository.findById(id).orElse(null);
    }
}
