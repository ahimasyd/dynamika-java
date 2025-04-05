package org.example.dynamika.assignment.service.validator;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.example.dynamika.assignment.commons.ValidationException;
import org.example.dynamika.assignment.commons.NotFoundException;
import org.example.dynamika.assignment.dto.book.BookDto;
import org.example.dynamika.assignment.dto.book.BookSearchRequest;
import org.example.dynamika.assignment.dto.commons.PagingRequestDto;
import org.example.dynamika.assignment.persistence.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookValidator {

    private final BookRepository bookRepository;


    public void validateSearch(PagingRequestDto<BookSearchRequest> request) {
        if (request == null) {
            throw new ValidationException("Request should be not null");
        }

        if (request.getFilter() == null || request.getPage() == null || request.getSize() == null) {
            throw new ValidationException("Request page, size and filter should be not null");
        }

        if (request.getPage() < 0) {
            throw new ValidationException("Request page should be at least 0");
        }

        if (request.getSize() < 1) {
            throw new ValidationException("Request page size should be at least 1");
        }
    }

    public void validateSave(BookDto dto) {
        if (dto == null) {
            throw new ValidationException("Save request should not be null");
        }

        if (StringUtils.isBlank(dto.getIsbn()) || StringUtils.isBlank(dto.getTitle())) {
            throw new ValidationException("Book ISBN code and title should be not null");
        }
    }

    public void validateIsbnIsFree(String isbn) {
        if (bookRepository.existsByIsbn(isbn)) {
            throw new NotFoundException("Book with provided ISBN code already exists");
        }
    }

}
