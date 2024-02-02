package com.janioofi.helpdesk.resources;

import com.janioofi.helpdesk.domain.dtos.ChamadoDTO;
import com.janioofi.helpdesk.domain.models.Chamado;
import com.janioofi.helpdesk.services.ChamadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    @PostMapping
    public ResponseEntity<Chamado> create(@RequestBody @Valid ChamadoDTO objDTO){
        Chamado obj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri().path("/{id}")
                .buildAndExpand(obj.getId_chamado()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
}
