package tads.aranoua.testes.springboottestes.repository;

import org.springframework.data.repository.CrudRepository;
import tads.aranoua.testes.springboottestes.model.Cidade;

public interface CidadeRepository extends CrudRepository<Cidade, Long> {
}
