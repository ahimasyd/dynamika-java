package org.example.dynamika.assignment.dto.client;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Schema(description = "Объект клиента библиотеки")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDto {

    @Schema(description = "ID")
    private Long id;

    @Schema(description = "Имя")
    private String firstName;

    @Schema(description = "Фамилия")
    private String lastName;

    @Schema(description = "Отчество")
    private String patronymic;

    @Schema(description = "Дата рождения")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

}
