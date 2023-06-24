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
public class PaisControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void deveCadastrarPaisComValoresCorretos() throws Exception {
        URI uri = new URI("/api/paises");

        String jsonRequest = "{\"nome\": \"Mexico\"}";

        RequestBuilder request = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest);

        ResultMatcher status = MockMvcResultMatchers.status().is(201);

        String jsonResponse = "{\"id\":1,\"nome\":\"Mexico\"}";

        ResultMatcher contentResponse = MockMvcResultMatchers.content().json(jsonResponse);

        mvc.perform(request).andExpect(status).andExpect(contentResponse);
    }

    @Test
    public void naoPodeCadastrarPaisSemNome() throws Exception {
        URI uri = new URI("/api/paises");

        // Tento forçar a definição de ID dele
        String jsonRequest = "{\"nome\": null }";

        RequestBuilder request = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest);

        ResultMatcher status = MockMvcResultMatchers.status().is(400);

        mvc.perform(request).andExpect(status);
    }
}
