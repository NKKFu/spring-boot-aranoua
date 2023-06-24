package tads.aranoua.testes.springboottestes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import tads.aranoua.testes.springboottestes.enums.SituacaoSaude;

@Entity
public class Voluntario {
    // Todos campos são obrigatórios

    // Passaportes podem conter letras e números
    @Id
    @Column(unique = true)
    private String passaporteId;

    @Column(nullable = false)
    private String nomeCompleto;

    // Idade de quando a pessoa se cadastrou, não importa a data atualmente dela
    @Column(nullable = false)
    private Integer idade;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String tipoSanguineo;

    @Column(nullable = false)
    private SituacaoSaude situacaoSaude;

    @ManyToOne(optional = false)
    private Cidade cidade;

    public String getPassaporteId() {
        return passaporteId;
    }

    public void setPassaporteId(String passaporteId) {
        this.passaporteId = passaporteId;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public SituacaoSaude getSituacaoSaude() {
        return situacaoSaude;
    }

    public void setSituacaoSaude(SituacaoSaude situacaoSaude) {
        this.situacaoSaude = situacaoSaude;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }
}
