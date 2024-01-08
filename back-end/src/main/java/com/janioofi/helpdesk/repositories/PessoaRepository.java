package com.janioofi.helpdesk.repositories;

import com.janioofi.helpdesk.domain.models.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
}
