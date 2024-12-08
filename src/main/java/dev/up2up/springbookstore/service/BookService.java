package dev.up2up.springbookstore.service;

import dev.up2up.springbookstore.entity.Book;
import dev.up2up.springbookstore.model.CreateBookRequest;
import dev.up2up.springbookstore.model.BookDto;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<BookDto> getAllBooks();
    Optional<Book> getBookById(Long id);
    BookDto createBook(CreateBookRequest createBookRequest);
    Book updateBook(Long id, Book book);
    void deleteBook(Long id);
}