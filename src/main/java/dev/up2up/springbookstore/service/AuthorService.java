package dev.up2up.springbookstore.service;

import java.util.List;

import dev.up2up.springbookstore.entity.Author;
import dev.up2up.springbookstore.model.AuthorDto;
import dev.up2up.springbookstore.model.CreateAuthorRequest;

public interface AuthorService {
    // Get all authors
    List<Author> getAllAuthors();

    // Get an author by ID
    Author getAuthorById(Long id);

    // Create a new author
    AuthorDto createAuthor(CreateAuthorRequest createAuthorRequest);

    // Update an existing author
    Author updateAuthor(Long id, Author updatedAuthor);

    // Delete an author
    void deleteAuthor(Long id);
    
}
