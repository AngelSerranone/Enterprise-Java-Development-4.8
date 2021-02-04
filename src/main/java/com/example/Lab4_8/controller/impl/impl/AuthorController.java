package com.example.Lab4_8.controller.impl.impl;
import com.example.Lab4_8.controller.impl.interfaces.IAuthorController;
import com.example.Lab4_8.model.Author;
import com.example.Lab4_8.service.interfaces.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class AuthorController implements IAuthorController {

    @Autowired
    private IAuthorService authorService;

    @PostMapping("/new-author")
    @ResponseStatus(HttpStatus.CREATED)
    public Author addNewAuthor(@RequestBody @Valid Author author) {
        return authorService.addNewAuthor(author);
    }

    @PutMapping("update-author/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateAuthor(@PathVariable Long id, @RequestBody @Valid Author author) {
        authorService.updateAuthor(id, author);
    }

    @DeleteMapping("/delete-author/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}
