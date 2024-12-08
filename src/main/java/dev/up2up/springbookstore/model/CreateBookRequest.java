package dev.up2up.springbookstore.model;

import java.math.BigDecimal;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Request model for creating a new book")
public class CreateBookRequest {

    @NotBlank
    @Size(max = 255)
    @Schema(description = "Title of the book", example = "Harry Potter and the Sorcerer's Stone", requiredMode = Schema.RequiredMode.REQUIRED)
    private String title;

    @NotBlank
    @Size(max = 13)
    @Schema(description = "ISBN of the book", example = "9780747532699", requiredMode = Schema.RequiredMode.REQUIRED)
    private String isbn;

    @NotNull
    @Schema(description = "Price of the book", example = "19.99", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal price;

    @NotNull
    @Schema(description = "ID of the author associated with the book", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long authorId;
}
