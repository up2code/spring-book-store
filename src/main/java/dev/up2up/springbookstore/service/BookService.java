package dev.up2up.springbookstore.service;

import dev.up2up.springbookstore.model.CreateBookRequest;
import dev.up2up.springbookstore.model.UpdateBookRequest;
import dev.up2up.springbookstore.model.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAllBooks();
    BookDto getBookById(Long id);
    BookDto createBook(CreateBookRequest createBookRequest);
    BookDto updateBook(Long id, UpdateBookRequest book);
    void deleteBook(Long id);
}