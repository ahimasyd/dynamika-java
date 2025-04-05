package org.example.dynamika.assignment.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.example.dynamika.assignment.dto.commons.SearchRequestDto;

@Schema(description = "Запрос на поиск клиентов")
@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
public class ClientSearchRequest extends SearchRequestDto<ClientOrderField> {

    // Тут могли быть ваши фильтры

}
