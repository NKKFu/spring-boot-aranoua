package tads.aranoua.testes.springboottestes.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import tads.aranoua.testes.springboottestes.model.Cidade;

@DataJpaTest
public class CidadeRepositoryTest {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private PaisRepository paisRepository;

    @Test
    public void deveCadastrarCidadeComDadosCompletos() {
        Cidade cidade = new Cidade();

        cidade.setId(1L);
        cidade.setNome("Manacapuru");
        cidade.setPais(paisRepository.findById(0L).get());

        cidadeRepository.save(cidade);

        Cidade cidadeNoBanco = cidadeRepository.findById(cidade.getId()).get();
        Assertions.assertEquals(cidade.getNome(), cidadeNoBanco.getNome());
    }

    @Test
    public void naoDeveCadastrarCidadeSemPais() {
        Cidade cidade = new Cidade();

        cidade.setId(1L);
        cidade.setNome("Manacapuru");
        cidade.setPais(null);

        // Se já estiver cadastrado o IBGE lança a exceção de DataIntegrity.
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> cidadeRepository.save(cidade));
    }
}
