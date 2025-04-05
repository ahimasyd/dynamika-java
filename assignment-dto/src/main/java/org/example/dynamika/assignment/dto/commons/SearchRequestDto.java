package org.example.dynamika.assignment.dto.commons;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Schema(description = "Базовый запрос на поиск")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class SearchRequestDto<T extends Enum<T>> {

    @Schema(description = "Порядок сортировки")
    private List<OrderDto<T>> orders;

}
