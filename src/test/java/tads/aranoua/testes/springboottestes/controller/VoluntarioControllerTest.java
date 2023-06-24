package tads.aranoua.testes.springboottestes.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
public class VoluntarioControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void deveCadastrarVoluntarioComValoresCorretos() throws Exception {
        URI uri = new URI("/api/voluntarios");

        String jsonRequest = "{\"passaporteId\":\"AA0001\",\"nomeCompleto\":\"Nelson Filho\",\"idade\":18,\"telefone\":\"+55 98888-4444\",\"email\":\"nelsonfilho@ifam.edu.br\",\"tipoSanguineo\":\"A+\",\"situacaoSaude\":\"RUIM\",\"cidade\":{\"id\":0}}";

        RequestBuilder request = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest);

        ResultMatcher status = MockMvcResultMatchers.status().is(201);

        String jsonResponse = "{\"passaporteId\":\"AA0001\",\"nomeCompleto\":\"Nelson Filho\",\"idade\":18,\"telefone\":\"+55 98888-4444\",\"email\":\"nelsonfilho@ifam.edu.br\",\"tipoSanguineo\":\"A+\",\"situacaoSaude\":\"RUIM\",\"cidade\":{\"id\":0,\"nome\":\"Brasilia\",\"pais\":{\"id\":0,\"nome\":\"Brasil\"}}}";

        ResultMatcher contentResponse = MockMvcResultMatchers.content().json(jsonResponse);

        mvc.perform(request).andExpect(status).andExpect(contentResponse);
    }

    @Test
    public void naoDeveCadastrarVoluntarioMenorDeIdade() throws Exception {
        URI uri = new URI("/api/voluntarios");

        // Tento forçar a definição de ID dele
        String jsonRequest = "{\"passaporteId\":\"AA0001\",\"nomeCompleto\":\"Nelson Filho\",\"idade\":16,\"telefone\":\"+55 98888-4444\",\"email\":\"nelsonfilho@ifam.edu.br\",\"tipoSanguineo\":\"A+\",\"situacaoSaude\":\"RUIM\",\"cidade\":{\"id\":0}}";

        RequestBuilder request = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest);

        ResultMatcher status = MockMvcResultMatchers.status().is(400);
        mvc.perform(request).andExpect(status);
    }
}
