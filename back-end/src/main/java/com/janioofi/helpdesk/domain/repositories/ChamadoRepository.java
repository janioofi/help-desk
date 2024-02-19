package com.janioofi.helpdesk.domain.repositories;

import com.janioofi.helpdesk.domain.models.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {
}
