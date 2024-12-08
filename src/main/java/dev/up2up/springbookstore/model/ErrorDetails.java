package dev.up2up.springbookstore.model;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Standard error response")
public class ErrorDetails {

    @Schema(description = "Error message", example = "Book not found")
    private String message;

    @Schema(description = "Detailed description of the error", example = "The requested book ID does not exist in the database.")
    private String details;

    @Schema(description = "Timestamp when the error occurred", example = "2024-12-07T10:15:30Z")
    private LocalDateTime timestamp;
}
