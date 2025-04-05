package org.example.dynamika.assignment.dto.book;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.example.dynamika.assignment.dto.commons.SearchRequestDto;

@Schema(description = "Запрос на поиск книг")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class BookSearchRequest extends SearchRequestDto<BookOrderField> {

    // Тут могли быть ваши фильтры

}
