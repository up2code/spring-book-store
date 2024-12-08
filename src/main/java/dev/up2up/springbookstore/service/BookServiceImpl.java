package dev.up2up.springbookstore.service;

import dev.up2up.springbookstore.entity.Author;
import dev.up2up.springbookstore.entity.Book;
import dev.up2up.springbookstore.exception.ResourceNotFoundException;
import dev.up2up.springbookstore.mapper.BookMapper;
import dev.up2up.springbookstore.model.BookDto;
import dev.up2up.springbookstore.model.CreateBookRequest;
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
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public BookDto createBook(CreateBookRequest createBookRequest) {
        // Validate if the author exists
        Author author = authorRepository.findById(createBookRequest.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + createBookRequest.getAuthorId()));

        // Map CreateBookRequest to Book entity
        Book book = new Book();
        book.setTitle(createBookRequest.getTitle());
        book.setIsbn(createBookRequest.getIsbn());
        book.setPrice(createBookRequest.getPrice());
        book.setAuthor(author);
        
        // Save and convert to DTO
        return convertToDto(bookRepository.save(book));
    }

    private BookDto convertToDto(Book book) {
        return BookDto.builder()
                .id(book.getId())
                .title(book.getTitle())
                .isbn(book.getIsbn())
                .price(book.getPrice())
                .authorId(book.getAuthor().getId())
                .authorName(book.getAuthor().getName())
                .build();
    }

    @Override
    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));

        book.setTitle(bookDetails.getTitle());
        book.setIsbn(bookDetails.getIsbn());
        book.setPrice(bookDetails.getPrice());
        book.setAuthor(bookDetails.getAuthor());

        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        bookRepository.delete(book);
    }
}