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
public class CidadeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void deveCadastrarCidadeComValoresCorretos() throws Exception {
        URI uri = new URI("/api/cidades");

        String jsonRequest = "{ \"nome\": \"Brasilia\", \"pais\": { \"id\": 0 }}";

        RequestBuilder request = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest);

        ResultMatcher status = MockMvcResultMatchers.status().is(201);

        String jsonResponse = "{\"id\":1,\"nome\":\"Brasilia\",\"pais\":{\"id\":0,\"nome\":\"Brasil\"}}";

        ResultMatcher contentResponse = MockMvcResultMatchers.content().json(jsonResponse);

        mvc.perform(request).andExpect(status).andExpect(contentResponse);
    }

    @Test
    public void naoPodeCadastrarCidadeComIdDefinido() throws Exception {
        URI uri = new URI("/api/cidades");

        // Tento forçar a definição de ID dele
        String jsonRequest = "{\"id\":99,\"nome\":\"Brasilia\",\"pais\":{\"id\":0,\"nome\":\"Brasil\"}}";

        RequestBuilder request = MockMvcRequestBuilders
                .post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonRequest);

        ResultMatcher status = MockMvcResultMatchers.status().is(201);

        mvc.perform(request).andExpect(status);
    }
}
