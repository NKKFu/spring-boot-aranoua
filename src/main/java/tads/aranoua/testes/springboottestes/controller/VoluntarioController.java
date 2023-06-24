package tads.aranoua.testes.springboottestes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import tads.aranoua.testes.springboottestes.model.Cidade;
import tads.aranoua.testes.springboottestes.model.Voluntario;
import tads.aranoua.testes.springboottestes.repository.CidadeRepository;
import tads.aranoua.testes.springboottestes.repository.VoluntarioRepository;

import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/voluntarios")
public class VoluntarioController {
    @Autowired
    private VoluntarioRepository voluntarioRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping
    @ResponseBody
    public List<Voluntario> list() {
        // Convert Iterable to List
        Iterator<Voluntario> iterator = voluntarioRepository.findAll().iterator();
        ArrayList<Voluntario> result = new ArrayList<Voluntario>();
        while (iterator.hasNext()) {
            result.add(iterator.next());
        }
        return result;
    }

    @ResponseBody
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Voluntario> find(@PathVariable("id") String id) {
        return voluntarioRepository.findById(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Voluntario> Create(@RequestBody Voluntario voluntario, UriComponentsBuilder uriComponentsBuilder) {
        Optional<Cidade> cidade = cidadeRepository.findById(voluntario.getCidade().getId());

        // If already exists this voluntario in db
        if (voluntarioRepository.findById(voluntario.getPassaporteId()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        if (voluntario.getIdade() < 18 || voluntario.getIdade() > 55) {
            return ResponseEntity.badRequest().build();
        }

        if (cidade.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        voluntario.setCidade(cidade.get());
        Voluntario savedVoluntario = voluntarioRepository.save(voluntario);
        URI path = uriComponentsBuilder.path("/api/voluntarios/{id}")
                .buildAndExpand(savedVoluntario.getPassaporteId())
                .toUri();
        return ResponseEntity.created(path).body(savedVoluntario);
    }
}
