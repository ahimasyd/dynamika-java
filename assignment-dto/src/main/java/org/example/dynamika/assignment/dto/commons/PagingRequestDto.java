package org.example.dynamika.assignment.dto.commons;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "Запрос на поиск с пагинацией")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagingRequestDto<T> {

    @Schema(description = "Номер страницы")
    private Integer page;

    @Schema(description = "Размер страницы")
    private Integer size;

    @Schema(description = "Тело запроса")
    private T filter;

}
