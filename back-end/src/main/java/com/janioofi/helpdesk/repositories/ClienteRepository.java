package com.janioofi.helpdesk.repositories;

import com.janioofi.helpdesk.domain.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
