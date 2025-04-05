package org.example.dynamika.assignment.service.mapper;

import lombok.RequiredArgsConstructor;
import org.example.dynamika.assignment.dto.book.BookDto;
import org.example.dynamika.assignment.persistence.entity.Book;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookMapper {

    private final ModelMapper modelMapper;


    public BookDto toDto(Book entity) {
        return modelMapper.map(entity, BookDto.class);
    }

    public Book toEntity(BookDto dto) {
        return modelMapper.map(dto, Book.class);
    }

}
