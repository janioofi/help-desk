package com.janioofi.helpdesk.resources;

import com.janioofi.helpdesk.domain.dtos.TecnicoDTO;
import com.janioofi.helpdesk.domain.models.Tecnico;
import com.janioofi.helpdesk.services.TecnicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tecnicos")
public class TecnicoResource {
    private final TecnicoService service;

    public TecnicoResource(TecnicoService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tecnico> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Tecnico>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Tecnico> create(@RequestBody TecnicoDTO tecnico){
        Tecnico data = service.create(tecnico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(data.getId_pessoa()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
