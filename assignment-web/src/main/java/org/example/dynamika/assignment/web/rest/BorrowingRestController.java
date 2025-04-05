package org.example.dynamika.assignment.web.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.dynamika.assignment.dto.borrowing.BorrowingFlattenedDto;
import org.example.dynamika.assignment.service.service.BorrowingService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Tag(name = "API одалживания книг")
@RestController
@RequestMapping("/api/borrowings")
@RequiredArgsConstructor
public class BorrowingRestController {

    private final BorrowingService borrowingService;


    @Operation(description = "Получить все незакрытые одалживания в сплющенном представлении")
    @GetMapping(value = "/ongoing/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<BorrowingFlattenedDto> getAllOngoing() {
        return borrowingService.getAllOngoing();
    }

}
