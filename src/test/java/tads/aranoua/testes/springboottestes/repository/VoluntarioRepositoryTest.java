package tads.aranoua.testes.springboottestes.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;

import tads.aranoua.testes.springboottestes.enums.SituacaoSaude;
import tads.aranoua.testes.springboottestes.model.Voluntario;

@DataJpaTest
public class VoluntarioRepositoryTest {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private VoluntarioRepository voluntarioRepository;

    @Test
    public void deveCadastrarVoluntarioComDadosCompletos() {
        Voluntario voluntario = new Voluntario();

        voluntario.setPassaporteId("BC1287312");
        voluntario.setNomeCompleto("Nelsinho");
        voluntario.setIdade(28);
        voluntario.setTelefone("TELEFONE VALIDO");
        voluntario.setEmail("EMAIL VALIDO");
        voluntario.setTipoSanguineo("O-");
        voluntario.setSituacaoSaude(SituacaoSaude.BOM);
        voluntario.setCidade(cidadeRepository.findById(0L).get());

        voluntarioRepository.save(voluntario);

        Voluntario voluntarioNoBanco = voluntarioRepository.findById(voluntario.getPassaporteId()).get();
        Assertions.assertEquals(voluntario.getPassaporteId(), voluntarioNoBanco.getPassaporteId());
    }

    @Test
    public void naoDeveCadastrarVoluntarioSemCidade() {
        Voluntario voluntario1 = new Voluntario();
    
        voluntario1.setPassaporteId("BC1287312");
        voluntario1.setNomeCompleto("Nelsinho");
        voluntario1.setIdade(28);
        voluntario1.setTelefone("TELEFONE VALIDO");
        voluntario1.setEmail("EMAIL VALIDO");
        voluntario1.setTipoSanguineo("O-");
        voluntario1.setSituacaoSaude(SituacaoSaude.BOM);
        voluntario1.setCidade(null);

        // Se já estiver cadastrado o IBGE lança a exceção de DataIntegrity.
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> voluntarioRepository.save(voluntario1));
    }
}
