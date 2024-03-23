package com.janioofi.helpdesk.services;

import com.janioofi.helpdesk.domain.dtos.ClienteDTO;
import com.janioofi.helpdesk.domain.dtos.TecnicoDTO;
import com.janioofi.helpdesk.domain.enums.Perfil;
import com.janioofi.helpdesk.domain.models.Cliente;
import com.janioofi.helpdesk.domain.models.Pessoa;
import com.janioofi.helpdesk.exceptions.BusinessRuntimeException;
import com.janioofi.helpdesk.exceptions.RecordNotFoundException;
import com.janioofi.helpdesk.domain.repositories.ClienteRepository;
import com.janioofi.helpdesk.domain.repositories.PessoaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    private final ClienteRepository repository;
    private final PessoaRepository pessoaRepository;
    private final BCryptPasswordEncoder encoder;

    public ClienteService(ClienteRepository repository, PessoaRepository pessoaRepository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.pessoaRepository = pessoaRepository;
        this.encoder = encoder;
    }

    public Cliente findById(Integer id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Nenhum cliente encontrado com o id: " + id));
    }

    public List<Cliente> findAll(){
        return repository.findAll();
    }

    public Cliente create(ClienteDTO data){
        validaPorCpfEEmail(data);
        data.setSenha(encoder.encode(data.getSenha()));
        Cliente cliente = new Cliente(data);
        return repository.save(cliente);
    }

    public Cliente update(Integer id, ClienteDTO objDTO){
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
        }).orElseThrow(() -> new RecordNotFoundException("Nenhum cliente encontrado com o id: " + id));
    }

    public void deleteById(Integer id){
        Cliente obj = findById(id);
        if(!obj.getChamados().isEmpty()){
            throw new DataIntegrityViolationException("Cliente possui ordens de serviço e não pode ser deletado!");
        }
        repository.deleteById(id);
    }

    private void validaPorCpfEEmail(ClienteDTO objDTO) {
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
