package org.example.dynamika.assignment.service.service;

import org.example.dynamika.assignment.dto.book.BookDto;
import org.example.dynamika.assignment.dto.book.BookSearchRequest;
import org.example.dynamika.assignment.dto.commons.PagingRequestDto;
import org.example.dynamika.assignment.dto.commons.PagingResponseDto;

public interface BookService {

    BookDto getById(Long id);

    PagingResponseDto<BookDto> search(PagingRequestDto<BookSearchRequest> request);

    BookDto save(BookDto book);

}
