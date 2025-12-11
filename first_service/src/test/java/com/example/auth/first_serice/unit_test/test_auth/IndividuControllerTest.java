package com.example.auth.first_serice.unit_test.test_auth;

//public class IndividuControllerTest {
//package com.example.auth.first_serice.test_auth;

import com.example.auth.first_serice.controller.IndividuController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(IndividuController.class)
public class IndividuControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetMethodNameWithValidParam() throws Exception {
        mockMvc.perform(get("/indiidu/path").param("param", "test"))
                .andExpect(status().isOk())
                .andExpect(content().string("test"));
    }

    @Test
    public void testGetMethodNameWithEmptyParam() throws Exception {
        mockMvc.perform(get("/indiidu/path").param("param", ""))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void testGetMethodNameWithoutParam() throws Exception {
        mockMvc.perform(get("/indiidu/path"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetMethodNameWithSpecialCharacters() throws Exception {
        mockMvc.perform(get("/indiidu/path").param("param", "test@123#"))
                .andExpect(status().isOk())
                .andExpect(content().string("test@123#"));
    }

     @Test
    public void testCreateIndividualWithValidRequest() throws Exception {
        mockMvc.perform(post("/indiidu/add")
                .contentType("application/json")
                .content("{\"name\":\"John\",\"age\":30}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateIndividualWithEmptyRequest() throws Exception {
        mockMvc.perform(post("/indiidu/add")
                .contentType("application/json")
                .content("{}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateIndividualWithInvalidJson() throws Exception {
        mockMvc.perform(post("/indiidu/add")
                .contentType("application/json")
                .content("invalid json"))
                .andExpect(status().isBadRequest());
    }
}


