package com.janioofi.helpdesk.services;

import com.janioofi.helpdesk.domain.dtos.TecnicoDTO;
import com.janioofi.helpdesk.domain.enums.Perfil;
import com.janioofi.helpdesk.domain.models.Tecnico;
import com.janioofi.helpdesk.exceptions.BusinessRuntimeException;
import com.janioofi.helpdesk.exceptions.RecordNotFoundException;
import com.janioofi.helpdesk.repositories.PessoaRepository;
import com.janioofi.helpdesk.repositories.TecnicoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
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
        validaEmailECpf(data);
        data.setSenha(encoder.encode(data.getSenha()));
        Tecnico tecnico = new Tecnico(data);
        return repository.save(tecnico);
    }

    public Tecnico update(Integer id, TecnicoDTO tecnico){
        Tecnico oldObj = findById(id);
        validaEmailECpf(tecnico);
        if(!(tecnico.getEmail() == null) && verificaEmail(tecnico)){
            oldObj.setEmail(tecnico.getEmail());
        }
        if(!(tecnico.getNome() == null)){
            oldObj.setNome(tecnico.getNome());
        }
        if(!(tecnico.getSenha() == null)){
            oldObj.setSenha(tecnico.getSenha());
        }
        if(!tecnico.getPerfis().isEmpty()){
            Set<Perfil> perfis = new HashSet<>();
            for(Perfil p : tecnico.getPerfis()){
                perfis.add(p);
            }
            oldObj.setPerfis(perfis);
        }
        return repository.save(oldObj);
    }

    public void deleteById(Integer id){
        Tecnico obj = findById(id);
        if(!obj.getChamados().isEmpty()){
            throw new DataIntegrityViolationException("Técnico possui ordens de serviço e não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private void validaEmailECpf(TecnicoDTO data){
        if(pessoaRepository.findByCpf(data.getCpf()).isPresent()){
            throw new BusinessRuntimeException("Já existe um cadastro com esse cpf!");
        }
        if(pessoaRepository.findByEmail(data.getEmail()).isPresent()){
            throw new BusinessRuntimeException("Já existe um cadastro com esse email!");
        }
    }

    private Boolean verificaEmail(TecnicoDTO data){
        return pessoaRepository.findByEmail(data.getEmail()).isEmpty();
    }

}
