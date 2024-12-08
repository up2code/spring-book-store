package dev.up2up.springbookstore.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Data Transfer Object representing an author")
public class AuthorDto {
    
    @Schema(description = "Unique identifier of the author", example = "1")
    private Long id;

    @Schema(description = "Name of the author", example = "J.K. Rowling")
    private String name;

    @Schema(description = "Biography of the author", example = "Author of the Harry Potter series")
    private String bio;
}
