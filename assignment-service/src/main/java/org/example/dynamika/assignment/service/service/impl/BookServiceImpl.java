package org.example.dynamika.assignment.service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.dynamika.assignment.commons.NotFoundException;
import org.example.dynamika.assignment.dto.book.BookDto;
import org.example.dynamika.assignment.dto.book.BookSearchRequest;
import org.example.dynamika.assignment.dto.commons.PagingRequestDto;
import org.example.dynamika.assignment.dto.commons.PagingResponseDto;
import org.example.dynamika.assignment.persistence.criteria.BookCriteria;
import org.example.dynamika.assignment.persistence.entity.Book;
import org.example.dynamika.assignment.persistence.repository.BookRepository;
import org.example.dynamika.assignment.service.mapper.BookMapper;
import org.example.dynamika.assignment.service.service.BookService;
import org.example.dynamika.assignment.service.validator.BookValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final BookValidator bookValidator;


    @Transactional(readOnly = true)
    @Override
    public BookDto getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Book not found by id"));
        return bookMapper.toDto(book);
    }

    @Transactional(readOnly = true)
    @Override
    public PagingResponseDto<BookDto> search(PagingRequestDto<BookSearchRequest> request) {
        bookValidator.validateSearch(request);

        Page<Book> resultPage = bookRepository.findAll(
                BookCriteria.search(request.getFilter()),
                PageRequest.of(request.getPage(), request.getSize())
        );

        List<BookDto> booksDtos = resultPage.stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
        return PagingResponseDto.<BookDto>builder()
                .page(resultPage.getNumber())
                .size(resultPage.getSize())
                .total(resultPage.getTotalElements())
                .data(booksDtos)
                .build();
    }

    @Transactional
    @Override
    public BookDto save(BookDto bookSaveRequest) {
        bookValidator.validateSave(bookSaveRequest);

        Book book;
        if (bookSaveRequest.getId() == null) {
            bookValidator.validateIsbnIsFree(bookSaveRequest.getIsbn());

            book = bookMapper.toEntity(bookSaveRequest);
        } else {
            book = bookRepository.findById(bookSaveRequest.getId())
                    .orElseThrow(() -> new NotFoundException("Book not found by id"));

            if (!Objects.equals(bookSaveRequest.getIsbn(), book.getIsbn())) {
                bookValidator.validateIsbnIsFree(bookSaveRequest.getIsbn());
            }

            book.setIsbn(bookSaveRequest.getIsbn());
            book.setTitle(bookSaveRequest.getTitle());
            book.setAuthor(bookSaveRequest.getAuthor());
        }

        book = bookRepository.save(book);
        return bookMapper.toDto(book);
    }

}
