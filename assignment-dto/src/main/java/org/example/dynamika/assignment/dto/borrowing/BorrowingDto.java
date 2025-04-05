package org.example.dynamika.assignment.dto.borrowing;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.dynamika.assignment.dto.book.BookDto;
import org.example.dynamika.assignment.dto.client.ClientDto;

import java.time.LocalDateTime;

@Schema(description = "Объект одалживания книги")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowingDto {

    @Schema(description = "ID одалживания")
    private Long id;

    @Schema(description = "Книга")
    private BookDto book;

    @Schema(description = "Клиент")
    private ClientDto client;

    @Schema(description = "Дата-время взятия книги")
    private LocalDateTime startDate;

    @Schema(description = "Дата-время возврата книги")
    private LocalDateTime endDate;

}
