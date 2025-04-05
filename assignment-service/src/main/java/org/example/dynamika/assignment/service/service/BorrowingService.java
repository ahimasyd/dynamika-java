package org.example.dynamika.assignment.service.service;

import org.example.dynamika.assignment.dto.borrowing.BorrowRequest;
import org.example.dynamika.assignment.dto.borrowing.BorrowingDto;
import org.example.dynamika.assignment.dto.borrowing.BorrowingFlattenedDto;

import java.util.Collection;

public interface BorrowingService {

    Collection<BorrowingFlattenedDto> getAllOngoing();

    BorrowingDto borrow(BorrowRequest request);

}
