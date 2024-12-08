package dev.up2up.springbookstore.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Data transfer object for Book")
public class BookDto {

    @Schema(description = "Unique identifier of the book", example = "1")
    private Long id;

    @Schema(description = "Title of the book", example = "Harry Potter")
    private String title;

    @Schema(description = "ISBN of the book", example = "9780747532699")
    private String isbn;

    @Schema(description = "Price of the book", example = "19.99")
    private BigDecimal price;

    @Schema(description = "Author identifier of the book", example = "1")
    private Long authorId;

    @Schema(description = "Author name of the book", example = "J.K. Rowling")
    private String authorName;

    @Schema(description = "Creation date of the book", example = "2024-01-01")
    private LocalDateTime createdAt;

    @Schema(description = "Update date of the book", example = "2024-01-01")
    private LocalDateTime updatedAt;
}
