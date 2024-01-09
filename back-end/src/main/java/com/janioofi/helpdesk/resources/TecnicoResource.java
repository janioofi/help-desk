package com.janioofi.helpdesk.resources;

import com.janioofi.helpdesk.domain.models.Tecnico;
import com.janioofi.helpdesk.services.TecnicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
