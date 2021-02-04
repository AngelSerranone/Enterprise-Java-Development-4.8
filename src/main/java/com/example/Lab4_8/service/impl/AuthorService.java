package com.example.Lab4_8.service.impl;

import com.example.Lab4_8.model.Author;
import com.example.Lab4_8.model.Post;
import com.example.Lab4_8.repository.AuthorRepository;
import com.example.Lab4_8.service.interfaces.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements IAuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public void saveAll(List<Author> authorList) {
        authorRepository.saveAll(authorList);
    }

    public void deleteAll() {
        authorRepository.deleteAll();
    }

    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author addNewAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void updateAuthor(Long id, Author author) {
        if (authorRepository.findById(id).isPresent()) {
            author.setId(id);
            authorRepository.save(author);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found");
        }
    }

    @Override
    public void deleteAuthor(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()){
            authorRepository.delete(author.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Author not found");
        }
    }



}
