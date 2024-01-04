package com.janioofi.helpdesk.repositories;

import com.janioofi.helpdesk.domain.Chamado;
import com.janioofi.helpdesk.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
