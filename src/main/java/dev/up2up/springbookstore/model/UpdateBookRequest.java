package dev.up2up.springbookstore.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateBookRequest {
    private String title;
    private String isbn;
    private BigDecimal price;
    private Long authorId;
}
