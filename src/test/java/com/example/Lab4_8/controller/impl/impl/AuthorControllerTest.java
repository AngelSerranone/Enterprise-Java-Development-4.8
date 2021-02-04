package com.example.Lab4_8.controller.impl.impl;

import com.example.Lab4_8.model.Author;
import com.example.Lab4_8.service.impl.AuthorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.UnsupportedEncodingException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AuthorControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private AuthorService authorService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Author author1 = new Author("Aiko tanaka");
        Author author2 = new Author("Emilia Pardo");
        authorService.saveAll(List.of(author1,author2));


    }

    @AfterEach
    void tearDown() {
        authorService.deleteAll();
    }

    @Test
    void addNewAuthor() throws Exception {
        Author author = new Author("Pedro");
        String body = objectMapper.writeValueAsString(author);
        MvcResult result = mockMvc.perform(
                post("/new-author")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Pedro"));
    }

    @Test
    void updateAuthor() throws Exception {
        Long index = authorService.findAll().get(0).getId();
        String body = "{\"name\": \"José Navarro\"}";
        MvcResult result = mockMvc.perform(
                put("/update-author/" + index)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent()).andReturn();
    }

    @Test
    void deleteAuthor() throws Exception {
        Long index = authorService.findAll().get(0).getId();  // This is used to avoid problems with auto_increment ids.
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.delete("/delete-author/" + index)
        ).andExpect(status().isNoContent()).andReturn();
    }
}