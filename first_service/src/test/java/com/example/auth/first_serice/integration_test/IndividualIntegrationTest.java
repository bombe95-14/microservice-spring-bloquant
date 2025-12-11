
package com.example.auth.first_serice.integration_test;

import com.example.auth.first_serice.controller.IndividuController;
import com.example.auth.first_serice.entity.Individu;
import com.example.auth.first_serice.repository.IndividuRepository;

import tools.jackson.databind.ObjectMapper;

//import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class IndividualIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private IndividuRepository individuRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        individuRepository.deleteAll();
    }

    @Test
    public void testCreateAndRetrieveIndividual() throws Exception {
        mockMvc.perform(post("/indiidu/add")
                .contentType("application/json")
                .content("{\"name\":\"Alice\",\"age\":28}"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/indiidu/path").param("param", "Alice"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateIndividualAndVerifyDatabase() throws Exception {
        mockMvc.perform(post("/indiidu/add")
                .contentType("application/json")
                .content("{\"name\":\"Bob\",\"age\":35}"))
                .andExpect(status().isOk());

        assert individuRepository.findAll().size() == 1;
    }

    @Test
    public void testCreateMultipleIndividuals() throws Exception {
        mockMvc.perform(post("/indiidu/add")
                .contentType("application/json")
                .content("{\"name\":\"Charlie\",\"age\":40}"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/indiidu/add")
                .contentType("application/json")
                .content("{\"name\":\"Diana\",\"age\":32}"))
                .andExpect(status().isOk());

        assert individuRepository.findAll().size() == 2;
    }

    @Test
    public void testCreateIndividualWithDuplicateName() throws Exception {
        mockMvc.perform(post("/indiidu/add")
                .contentType("application/json")
                .content("{\"name\":\"Eve\",\"age\":29}"))
                .andExpect(status().isOk());

        mockMvc.perform(post("/indiidu/add")
                .contentType("application/json")
                .content("{\"name\":\"Eve\",\"age\":30}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateIndividualWithMissingFields() throws Exception {
        mockMvc.perform(post("/indiidu/add")
                .contentType("application/json")
                .content("{\"name\":\"Frank\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateIndividualWithNegativeAge() throws Exception {
        mockMvc.perform(post("/indiidu/add")
                .contentType("application/json")
                .content("{\"name\":\"Grace\",\"age\":-5}"))
                .andExpect(status().isBadRequest());
    }
}