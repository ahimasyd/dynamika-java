package org.example.dynamika.assignment.service.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dynamika.assignment.commons.ConstraintException;
import org.example.dynamika.assignment.commons.NotFoundException;
import org.example.dynamika.assignment.dto.borrowing.BorrowRequest;
import org.example.dynamika.assignment.dto.borrowing.BorrowingDto;
import org.example.dynamika.assignment.dto.borrowing.BorrowingFlattenedDto;
import org.example.dynamika.assignment.persistence.entity.Book;
import org.example.dynamika.assignment.persistence.entity.Borrowing;
import org.example.dynamika.assignment.persistence.entity.Client;
import org.example.dynamika.assignment.persistence.repository.BookRepository;
import org.example.dynamika.assignment.persistence.repository.BorrowingRepository;
import org.example.dynamika.assignment.persistence.repository.ClientRepository;
import org.example.dynamika.assignment.service.mapper.BorrowingMapper;
import org.example.dynamika.assignment.service.service.BorrowingService;
import org.example.dynamika.assignment.service.validator.BorrowingValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {

    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;
    private final BorrowingRepository borrowingRepository;
    private final BorrowingMapper borrowingMapper;
    private final BorrowingValidator borrowingValidator;


    @Transactional(readOnly = true)
    @Override
    public Collection<BorrowingFlattenedDto> getAllOngoing() {
        return borrowingRepository.findAll().stream()
                .map(borrowingMapper::toFlattenedDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public BorrowingDto borrow(BorrowRequest request) {
        borrowingValidator.validateBorrow(request);

        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new NotFoundException("Book not found by id=" + request.getBookId()));
        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new NotFoundException("Client not found by id=" + request.getClientId()));

        borrowingRepository.findOngoingByBookIdAndClientId(request.getBookId(), request.getClientId())
                .ifPresent(borrowing -> {
                    throw new ConstraintException("The client has already borrowed this book");
                });

        Borrowing borrowing = Borrowing.builder()
                .book(book)
                .client(client)
                .startDate(LocalDateTime.now())
                .build();
        borrowing = borrowingRepository.save(borrowing);

        return borrowingMapper.toDto(borrowing);
    }

}
