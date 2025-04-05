package org.example.dynamika.assignment.dto.borrowing;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(description = "Сплющенный объект одалживания книги")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowingFlattenedDto {

    @Schema(description = "IBSN код книги")
    private String bookIsbn;

    @Schema(description = "Заголовок книги")
    private String bookTitle;

    @Schema(description = "Автор книги")
    private String bookAuthor;


    @Schema(description = "Имя клиента")
    private String clientFirstName;

    @Schema(description = "Фамилия клиента")
    private String clientLastName;

    @Schema(description = "Отчество клиента")
    private String clientPatronymic;

    @Schema(description = "Дата рождения клиента")
    private LocalDate clientBirthDate;


    @Schema(description = "Дата-время взятия книги")
    private LocalDateTime startDate;

    @Schema(description = "Дата-время возврата книги")
    private LocalDateTime endDate;

}
