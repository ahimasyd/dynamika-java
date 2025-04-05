package org.example.dynamika.assignment.service.mapper;

import lombok.RequiredArgsConstructor;
import org.example.dynamika.assignment.dto.borrowing.BorrowingDto;
import org.example.dynamika.assignment.dto.borrowing.BorrowingFlattenedDto;
import org.example.dynamika.assignment.persistence.entity.Borrowing;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BorrowingMapper {

    private final ModelMapper modelMapper;


    public BorrowingDto toDto(Borrowing entity) {
        return modelMapper.map(entity, BorrowingDto.class);
    }

    public BorrowingFlattenedDto toFlattenedDto(Borrowing entity) {
        return modelMapper.map(entity, BorrowingFlattenedDto.class);
    }

    public Borrowing toEntity(BorrowingDto dto) {
        return modelMapper.map(dto, Borrowing.class);
    }

}
