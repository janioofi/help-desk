package com.janioofi.helpdesk.domain.repositories;

import com.janioofi.helpdesk.domain.models.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
