package dev.up2up.springbookstore.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import dev.up2up.springbookstore.entity.Author;
import dev.up2up.springbookstore.entity.Book;
import dev.up2up.springbookstore.exception.ResourceNotFoundException;
import dev.up2up.springbookstore.mapper.BookMapper;
import dev.up2up.springbookstore.model.BookDto;
import dev.up2up.springbookstore.model.CreateBookRequest;
import dev.up2up.springbookstore.repository.AuthorRepository;
import dev.up2up.springbookstore.repository.BookRepository;

@ExtendWith(MockitoExtension.class)
@DisplayName("BookService Unit Tests")
public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookMapper bookMapper;

    private Book book;

    private BookDto bookDto;

    @BeforeEach
    void setUp() {

        // Create a sample book
        Author author = new Author();
        author.setId(1L);
        author.setName("Author Name");

        book = Book.builder()
                .id(1L)
                .title("Sample Book")
                .isbn("1234567890")
                .price(BigDecimal.valueOf(19.99))
                .author(author)
                .build();

        bookDto = BookDto.builder()
                .id(1L)
                .title("Sample Book")
                .isbn("1234567890")
                .price(BigDecimal.valueOf(19.99))
                .authorId(1L)
                .authorName("Author Name")
                .build();
    }

    @Test
    @DisplayName("Should return all books when getAllBooks() is called")
    void testGetAllBooks() {
        // Arrange
        when(bookRepository.findAll()).thenReturn(List.of(book));
        when(bookMapper.toDto(book)).thenReturn(bookDto);

        // Act
        List<BookDto> books = bookService.getAllBooks();

        // Assert
        assertNotNull(books);
        assertEquals(1, books.size());

        BookDto bookDto = books.get(0);
        assertEquals(book.getId(), bookDto.getId());
        assertEquals(book.getTitle(), bookDto.getTitle());
        assertEquals(book.getIsbn(), bookDto.getIsbn());
        assertEquals(book.getPrice(), bookDto.getPrice());
        assertEquals(book.getAuthor().getId(), bookDto.getAuthorId());
        assertEquals(book.getAuthor().getName(), bookDto.getAuthorName());

        verify(bookRepository, times(1)).findAll();
        verify(bookMapper, times(1)).toDto(book);
    }

    @Test
    @DisplayName("Should return a book by ID when it exists")
    void testGetBookById_Success() {
        // Arrange
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookMapper.toDto(book)).thenReturn(bookDto);

        // Act
        var result = bookService.getBookById(1L);

        // Assert
        assertNotNull(result);
    }

    @Test
    @DisplayName("Should throw ResourceNotFoundException when book ID does not exist")
    void testGetBookById_Failure() {
        // Arrange
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            bookService.getBookById(1L);
        });
    }

    @Test
    @DisplayName("Should save and return the created book")
    void testCreateBook() {
        // Arrange
        var createBookRequest = new CreateBookRequest();
        createBookRequest.setTitle("Sample Book");
        createBookRequest.setIsbn("1234567890");
        createBookRequest.setPrice(BigDecimal.valueOf(19.99));
        createBookRequest.setAuthorId(1L);

        Author author = new Author();
        author.setId(1L);
        author.setName("Author Name");

        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(bookMapper.fromCreateRequestToEntity(createBookRequest)).thenReturn(book);
        when(bookRepository.save(book)).thenReturn(book);
        when(bookMapper.toDto(book)).thenReturn(bookDto);

        // Act
        var result = bookService.createBook(createBookRequest);

        // Assert
        assertNotNull(result);
        assertEquals(bookDto, result);
        verify(authorRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(book);
        verify(bookMapper, times(1)).fromCreateRequestToEntity(createBookRequest);
        verify(bookMapper, times(1)).toDto(book);
    }

}
