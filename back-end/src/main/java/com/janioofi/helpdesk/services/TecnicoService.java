package com.janioofi.helpdesk.services;

import com.janioofi.helpdesk.domain.dtos.TecnicoDTO;
import com.janioofi.helpdesk.domain.models.Tecnico;
import com.janioofi.helpdesk.exceptions.BusinessRuntimeException;
import com.janioofi.helpdesk.exceptions.RecordNotFoundException;
import com.janioofi.helpdesk.repositories.PessoaRepository;
import com.janioofi.helpdesk.repositories.TecnicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoService {
    private final TecnicoRepository repository;
    private final PessoaRepository pessoaRepository;

    public TecnicoService(TecnicoRepository repository, PessoaRepository pessoaRepository) {
        this.repository = repository;
        this.pessoaRepository = pessoaRepository;
    }

    public Tecnico findById(Integer id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Nenhum tecnico encontrado com o id: " + id));
    }

    public List<Tecnico> findAll(){
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO data){
        if(pessoaRepository.findByCpf(data.getCpf()).isPresent()){
            throw new BusinessRuntimeException("Já existe um cadastro com esse cpf!");
        }else if(pessoaRepository.findByEmail(data.getEmail()).isPresent()){
            throw new BusinessRuntimeException("Já existe um cadastro com esse email!");
        }
        else{
            Tecnico tecnico = new Tecnico(data);
            return repository.save(tecnico);
        }
    }
}
