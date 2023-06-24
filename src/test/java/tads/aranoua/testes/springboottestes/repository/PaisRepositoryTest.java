package tads.aranoua.testes.springboottestes.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import tads.aranoua.testes.springboottestes.model.Pais;

@DataJpaTest
public class PaisRepositoryTest {

    @Autowired
    private PaisRepository paisRepository;

    @Test
    public void deveCadastrarPaisComDadosCompletos() {
        Pais pais = new Pais();

        pais.setId(1L);
        pais.setNome("Argentina");

        paisRepository.save(pais);

        Pais paisNoBanco = paisRepository.findById(pais.getId()).get();
        Assertions.assertEquals(pais.getNome(), paisNoBanco.getNome());
    }

    @Test
    public void naoDeveCadastrarPaisSemNome() {
        Pais pais = new Pais();

        pais.setId(1L);
        pais.setNome(null);

        // Se já estiver cadastrado o IBGE lança a exceção de DataIntegrity.
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> paisRepository.save(pais));
    }
}
