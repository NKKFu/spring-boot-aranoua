package tads.aranoua.testes.springboottestes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import tads.aranoua.testes.springboottestes.model.Cidade;
import tads.aranoua.testes.springboottestes.repository.CidadeRepository;
import tads.aranoua.testes.springboottestes.repository.PaisRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController {
    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private PaisRepository paisRepository;

    @GetMapping
    @ResponseBody
    public List<Cidade> list() {
        // Convert Iterable to List
        Iterator<Cidade> iterator = cidadeRepository.findAll().iterator();
        ArrayList<Cidade> result = new ArrayList<Cidade>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return result;
    }

    @ResponseBody
    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Cidade> find(@PathVariable("id") Long id) {
        return cidadeRepository.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cidade> Create (@RequestBody Cidade cidade, UriComponentsBuilder uriComponentsBuilder) {
    cidade.setPais(paisRepository.findById(cidade.getPais().getId()).get());
        Cidade savedCidade = cidadeRepository.save(cidade);
        URI path = uriComponentsBuilder.path("/api/cidades/{id}")
                .buildAndExpand(savedCidade.getId())
                .toUri();
        return ResponseEntity.created(path).body(
            savedCidade
        );
    }
}
