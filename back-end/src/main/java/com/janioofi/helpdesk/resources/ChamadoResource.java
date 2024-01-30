package com.janioofi.helpdesk.resources;

import com.janioofi.helpdesk.domain.dtos.ChamadoDTO;
import com.janioofi.helpdesk.services.ChamadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/chamados")
public class ChamadoResource {

    private final ChamadoService service;

    public ChamadoResource(ChamadoService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }
}
