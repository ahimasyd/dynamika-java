package org.example.dynamika.assignment.dto.commons;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Collection;

@Schema(description = "Ответ с пагинацией")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PagingResponseDto<T> {

    @Schema(description = "Номер страницы")
    private Integer page;

    @Schema(description = "Размер страницы")
    private Integer size;

    @Schema(description = "Общее количество элементов")
    private Long total;

    @Schema(description = "Данные текущей страницы")
    private Collection<T> data;

}
