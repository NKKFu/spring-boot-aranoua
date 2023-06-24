package tads.aranoua.testes.springboottestes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import tads.aranoua.testes.springboottestes.model.Pais;
import tads.aranoua.testes.springboottestes.repository.PaisRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/paises")
public class PaisController {
    @Autowired
    private PaisRepository paisRepository;

    @GetMapping
    @ResponseBody
    public List<Pais> list() {
        // Convert Iterable to List
        Iterator<Pais> iterator = paisRepository.findAll().iterator();
        ArrayList<Pais> result = new ArrayList<Pais>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return result;
    }

    @ResponseBody
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Pais> find(@PathVariable("id") Long id) {
        return paisRepository.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Pais> Create(@RequestBody Pais pais, UriComponentsBuilder uriComponentsBuilder) {
        if (pais.getNome() == null) {
            return ResponseEntity.badRequest().build();
        }

        Pais savedPais = paisRepository.save(pais);
        URI path = uriComponentsBuilder.path("/api/paises/{id}")
                .buildAndExpand(savedPais.getId())
                .toUri();
        return ResponseEntity.created(path).body(savedPais);
    }
}
