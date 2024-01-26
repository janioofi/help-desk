package com.janioofi.helpdesk.resources;

import com.janioofi.helpdesk.domain.dtos.ClienteDTO;
import com.janioofi.helpdesk.domain.dtos.TecnicoDTO;
import com.janioofi.helpdesk.domain.models.Cliente;
import com.janioofi.helpdesk.domain.models.Tecnico;
import com.janioofi.helpdesk.services.ClienteService;
import com.janioofi.helpdesk.services.TecnicoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
    private final ClienteService service;

    public ClienteResource(ClienteService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable Integer id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> findAll(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @PostMapping
    public ResponseEntity<Cliente> create(@Valid @RequestBody ClienteDTO tecnico){
        Cliente data = service.create(tecnico);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(data.getId_pessoa()).toUri();
        return ResponseEntity.created(uri).body(data);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> update(@PathVariable Integer id, @Valid @RequestBody ClienteDTO tecnico){
        return ResponseEntity.status(HttpStatus.OK).body(service.update(id, tecnico));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Integer id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
