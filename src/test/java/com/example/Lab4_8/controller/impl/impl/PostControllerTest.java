package com.example.Lab4_8.controller.impl.impl;
import com.example.Lab4_8.model.Author;
import com.example.Lab4_8.model.Post;
import com.example.Lab4_8.service.impl.AuthorService;
import com.example.Lab4_8.service.impl.PostService;
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
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PostControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private PostService postService;

    @Autowired
    private AuthorService authorService;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        Author author1 = new Author("Pepe Navarro");
        Author author2 = new Author("Juan Martínez");
        authorService.saveAll(List.of(author1,author2));
        Post post1 = new Post(author1, "Vida Loca", "Blablabla....");
        Post post2 = new Post(author2, "El amanecer", "No es que me emocione otro amanecer, es que es el primero en que me vienes a ver...");
        postService.saveAll(List.of(post1,post2));
    }

    @AfterEach
    void tearDown() {
        postService.deleteAll();
        authorService.deleteAll();
    }

    @Test // not working :(
    void findPostAndAuthor() {
        List<Post> postList = postService.findAll();
        List<Object[]> result = postService.findPostAndAuthor(postList.get(0).getId());
        assertTrue(result.size() == 3);
    }

    @Test // not working :(
    void addNewPost() throws Exception {
        List<Author> authorList = authorService.findAll();        // This is used to avoid problems with auto_increment ids.
        String body = "{\"author\":" + authorList.get(0).getId() + ",\"title\": \"It works\",\"post\": \"Blabla...\"\n}";
        MvcResult result = mockMvc.perform(
                post("/new-post")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("works"));
    }

    @Test // not working :(
    void updatePostInformation() throws Exception {
        List<Post> postList = postService.findAll();  // This is used to avoid problems with auto_increment ids.
        Long index = authorService.findAll().get(0).getId();        // This is used to avoid problems with auto_increment ids.
        String body = "{\"author\":" + index + ",\"title\": \"It works\",\"post\": \"Blabla...\"\n}";
        MvcResult result = mockMvc.perform(
                put("/update-info/" + postList.get(0).getId())
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isNoContent()).andReturn();
    }

    @Test
    void deletePost() throws Exception {
        List<Post> blogPosts = postService.findAll();  // This is used to avoid problems with auto_increment ids.
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.delete("/delete-post/" + blogPosts.get(0).getId())
        ).andExpect(status().isNoContent()).andReturn();
    }
}