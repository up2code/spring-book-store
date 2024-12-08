package dev.up2up.springbookstore.controller;

import org.springframework.web.bind.annotation.RestController;

import dev.up2up.springbookstore.entity.Author;
import dev.up2up.springbookstore.model.AuthorDto;
import dev.up2up.springbookstore.model.CreateAuthorRequest;
import dev.up2up.springbookstore.model.ErrorDetails;
import dev.up2up.springbookstore.service.AuthorService;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/authors")
@Tag(name = "Authors", description = "Operations related to authors")
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping
    @Operation(summary = "Get all authors", description = "Retrieve a list of all authors in the database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all authors")
    })
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get an author by ID", description = "Retrieve an author by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the author"),
            @ApiResponse(responseCode = "404", description = "Author not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)))
    })
    public Author getAuthorById(@PathVariable Long id) {
        return authorService.getAuthorById(id);
    }

    @PostMapping
    @Operation(summary = "Create a new author", description = "Create a new author with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Author created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request body", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)))
    })
    public AuthorDto createAuthor(@RequestBody CreateAuthorRequest createAuthorRequest) {
        return authorService.createAuthor(createAuthorRequest);
    }

    // delete
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete an existing author", description = "Delete an existing author by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Author deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Author not found", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)))
    })
    public void deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
}
