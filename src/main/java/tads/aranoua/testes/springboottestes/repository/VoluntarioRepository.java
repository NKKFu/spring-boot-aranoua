package tads.aranoua.testes.springboottestes.repository;

import org.springframework.data.repository.CrudRepository;
import tads.aranoua.testes.springboottestes.model.Voluntario;

public interface VoluntarioRepository extends CrudRepository<Voluntario, String> {
}
