package org.example.dynamika.assignment.dto.borrowing;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Schema(description = "Запрос одалживания книги")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowRequest {

    @Schema(description = "ID книги")
    private Long bookId;

    @Schema(description = "ID клиента")
    private Long clientId;

}
