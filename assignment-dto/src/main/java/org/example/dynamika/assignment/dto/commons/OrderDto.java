package org.example.dynamika.assignment.dto.commons;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "Представление порядка по отдельно взятому полю")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDto<T extends Enum<T>> {

    @Schema(description = "Поле")
    private T field;

    @Schema(description = "Порядок")
    @Builder.Default
    private OrderDirection direction = OrderDirection.ASC;

}
