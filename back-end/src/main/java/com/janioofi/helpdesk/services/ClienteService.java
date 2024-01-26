package com.janioofi.helpdesk.services;

import com.janioofi.helpdesk.domain.dtos.ClienteDTO;
import com.janioofi.helpdesk.domain.enums.Perfil;
import com.janioofi.helpdesk.domain.models.Cliente;
import com.janioofi.helpdesk.exceptions.BusinessRuntimeException;
import com.janioofi.helpdesk.exceptions.RecordNotFoundException;
import com.janioofi.helpdesk.repositories.ClienteRepository;
import com.janioofi.helpdesk.repositories.PessoaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ClienteService {
    private final Logger logger = LoggerFactory.getLogger(ClienteService.class);
    private final ClienteRepository repository;
    private final PessoaRepository pessoaRepository;

    public ClienteService(ClienteRepository repository, PessoaRepository pessoaRepository) {
        this.repository = repository;
        this.pessoaRepository = pessoaRepository;
    }

    public Cliente findById(Integer id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Nenhum cliente encontrado com o id: " + id));
    }

    public List<Cliente> findAll(){
        return repository.findAll();
    }

    public Cliente create(ClienteDTO data){
        validaEmailECpf(data);
        Cliente cliente = new Cliente(data);
        return repository.save(cliente);
    }

    public Cliente update(Integer id, ClienteDTO cliente){
        Cliente oldObj = findById(id);
        validaEmailECpf(cliente);
        if(!(cliente.getEmail() == null) && verificaEmail(cliente)){
            oldObj.setEmail(cliente.getEmail());
        }
        if(!(cliente.getNome() == null)){
            oldObj.setNome(cliente.getNome());
        }
        if(!(cliente.getSenha() == null)){
            oldObj.setSenha(cliente.getSenha());
        }
        if(!cliente.getPerfis().isEmpty()){
            Set<Perfil> perfis = new HashSet<>();
            for(Perfil p : cliente.getPerfis()){
                perfis.add(p);
            }
            oldObj.setPerfis(perfis);
        }
        return repository.save(oldObj);
    }

    public void deleteById(Integer id){
        Cliente obj = findById(id);
        if(!obj.getChamados().isEmpty()){
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private void validaEmailECpf(ClienteDTO data){
        if(pessoaRepository.findByCpf(data.getCpf()).isPresent()){
            throw new BusinessRuntimeException("Já existe um cadastro com esse cpf!");
        }
        if(pessoaRepository.findByEmail(data.getEmail()).isPresent()){
            throw new BusinessRuntimeException("Já existe um cadastro com esse email!");
        }
    }

    private Boolean verificaEmail(ClienteDTO data){
        return pessoaRepository.findByEmail(data.getEmail()).isEmpty();
    }

}
