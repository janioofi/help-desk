package com.janioofi.helpdesk.services;

import com.janioofi.helpdesk.domain.dtos.TecnicoDTO;
import com.janioofi.helpdesk.domain.enums.Perfil;
import com.janioofi.helpdesk.domain.models.Pessoa;
import com.janioofi.helpdesk.domain.models.Tecnico;
import com.janioofi.helpdesk.exceptions.BusinessRuntimeException;
import com.janioofi.helpdesk.exceptions.RecordNotFoundException;
import com.janioofi.helpdesk.domain.repositories.PessoaRepository;
import com.janioofi.helpdesk.domain.repositories.TecnicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class TecnicoService {
    private final Logger logger = LoggerFactory.getLogger(TecnicoService.class);
    private final TecnicoRepository repository;
    private final PessoaRepository pessoaRepository;
    private final BCryptPasswordEncoder encoder;

    public TecnicoService(TecnicoRepository repository, PessoaRepository pessoaRepository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.pessoaRepository = pessoaRepository;
        this.encoder = encoder;
    }

    public Tecnico findById(Integer id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Nenhum tecnico encontrado com o id: " + id));
    }

    public List<Tecnico> findAll(){
        return repository.findAll();
    }

    public Tecnico create(TecnicoDTO data){
        validaPorCpfEEmail(data);
        data.setSenha(encoder.encode(data.getSenha()));
        Tecnico tecnico = new Tecnico(data);
        return repository.save(tecnico);
    }

    public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
        validaPorCpfEEmail(objDTO);
        return repository.findById(id).map(recordFound -> {
            recordFound.setNome(objDTO.getNome());
            if(!objDTO.getSenha().equals(recordFound.getSenha())){
                recordFound.setSenha(encoder.encode(objDTO.getSenha()));
            }
            recordFound.setEmail(objDTO.getEmail());
            recordFound.setDateCriacao(objDTO.getDateCriacao());
            recordFound.setPerfis(objDTO.getPerfis());
            return repository.save(recordFound);
        }).orElseThrow(() -> new RecordNotFoundException("Nenhum tecnico encontrado com o id: " + id));
    }

    public void deleteById(Integer id){
        Tecnico obj = findById(id);
        if(!obj.getChamados().isEmpty()){
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private void validaPorCpfEEmail(TecnicoDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId_pessoa() != objDTO.getId_pessoa()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId_pessoa() != objDTO.getId_pessoa()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }

}
