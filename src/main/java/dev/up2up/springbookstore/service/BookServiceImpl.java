package dev.up2up.springbookstore.service;

import dev.up2up.springbookstore.entity.Author;
import dev.up2up.springbookstore.entity.Book;
import dev.up2up.springbookstore.exception.ResourceNotFoundException;
import dev.up2up.springbookstore.mapper.BookMapper;
import dev.up2up.springbookstore.model.BookDto;
import dev.up2up.springbookstore.model.CreateBookRequest;
import dev.up2up.springbookstore.model.UpdateBookRequest;
import dev.up2up.springbookstore.repository.AuthorRepository;
import dev.up2up.springbookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorRepository authorRepository;

    private final BookMapper bookMapper;
    
    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        return bookMapper.toDto(book);
    }

    @Override
    public BookDto createBook(CreateBookRequest createBookRequest) {
        Author author = authorRepository.findById(createBookRequest.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + createBookRequest.getAuthorId()));

        Book book = bookMapper.fromCreateRequestToEntity(createBookRequest);
        book.setAuthor(author);

        return bookMapper.toDto(bookRepository.save(book));
    }

    @Override
    public BookDto updateBook(Long id, UpdateBookRequest updateBookRequest) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        bookMapper.updateEntityFromUpdateRequest(updateBookRequest, book);

        Book updatedBook = bookRepository.save(book);

        return bookMapper.toDto(updatedBook);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        bookRepository.delete(book);
    }
}