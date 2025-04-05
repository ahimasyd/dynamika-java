package org.example.dynamika.assignment.service.validator;

import lombok.RequiredArgsConstructor;
import org.example.dynamika.assignment.commons.ValidationException;
import org.example.dynamika.assignment.dto.borrowing.BorrowRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BorrowingValidator {

    public void validateBorrow(BorrowRequest request) {
        if (request == null) {
            throw new ValidationException("Request should be not null");
        }

        if (request.getBookId() == null || request.getClientId() == null) {
            throw new ValidationException("Request book ID and client ID should be not null");
        }
        if (request.getBookId() < 1 || request.getClientId() < 1) {
            throw new ValidationException("Request book ID and client ID should be at least 1");
        }
    }

}
