package com.example.Lab4_8.controller.impl.interfaces;
import com.example.Lab4_8.model.Author;
public interface IAuthorController {

    // add author
    public Author addNewAuthor(Author author);

    // update author
    public void updateAuthor(Long id, Author author);

    // delete author
    public void deleteAuthor(Long id);
}
