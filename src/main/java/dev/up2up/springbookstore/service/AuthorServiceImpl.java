package dev.up2up.springbookstore.service;

import java.util.List;

import org.springframework.stereotype.Service;

import dev.up2up.springbookstore.entity.Author;
import dev.up2up.springbookstore.exception.ResourceNotFoundException;
import dev.up2up.springbookstore.model.AuthorDto;
import dev.up2up.springbookstore.model.CreateAuthorRequest;
import dev.up2up.springbookstore.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
    }

    @Override
    public AuthorDto createAuthor(CreateAuthorRequest createAuthorRequest) {
        Author author = Author.builder()
                .name(createAuthorRequest.getName())
                .bio(createAuthorRequest.getBio())
                .build();


        Author savedAuthor = authorRepository.save(author);

        return AuthorDto.builder()
                .id(savedAuthor.getId())
                .name(savedAuthor.getName())
                .bio(savedAuthor.getBio())
                .build();
    }

    @Override
    public Author updateAuthor(Long id, Author updatedAuthor) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));

        existingAuthor.setName(updatedAuthor.getName());
        existingAuthor.setBio(updatedAuthor.getBio());
        return authorRepository.save(existingAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
        authorRepository.delete(author);
    }
}