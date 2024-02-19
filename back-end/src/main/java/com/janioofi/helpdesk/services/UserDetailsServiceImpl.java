package com.janioofi.helpdesk.services;

import com.janioofi.helpdesk.domain.models.Pessoa;
import com.janioofi.helpdesk.exceptions.RecordNotFoundException;
import com.janioofi.helpdesk.domain.repositories.PessoaRepository;
import com.janioofi.helpdesk.security.UserSS;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final PessoaRepository pessoaRepository;

    public UserDetailsServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Pessoa user = pessoaRepository.findByEmail(email).orElseThrow(() -> new RecordNotFoundException("Nenhuma pessa encontrada com esse email: " + email));
        return new UserSS(user.getId_pessoa(), user.getEmail(),user.getSenha(), user.getPerfis());
    }
}
