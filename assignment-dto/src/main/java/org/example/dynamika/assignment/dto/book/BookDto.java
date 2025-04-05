package org.example.dynamika.assignment.dto.book;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(description = "Объект книги")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDto {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "ISBN код")
    private String isbn;

    @Schema(description = "Заголовок")
    private String title;

    @Schema(description = "Автор")
    private String author;

}
