package org.example.dynamika.assignment.service.config;

import org.example.dynamika.assignment.dto.borrowing.BorrowingFlattenedDto;
import org.example.dynamika.assignment.persistence.entity.Borrowing;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "org.example.dynamika.assignment.service.mapper",
        "org.example.dynamika.assignment.service.validator",
        "org.example.dynamika.assignment.service.service"
})
public class ServiceConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.createTypeMap(Borrowing.class, BorrowingFlattenedDto.class)
                .addMapping(src -> src.getBook().getIsbn(), BorrowingFlattenedDto::setBookIsbn)
                .addMapping(src -> src.getBook().getTitle(), BorrowingFlattenedDto::setBookTitle)
                .addMapping(src -> src.getBook().getIsbn(), BorrowingFlattenedDto::setBookIsbn)
                .addMapping(src -> src.getBook().getAuthor(), BorrowingFlattenedDto::setBookAuthor)
                .addMapping(src -> src.getClient().getFirstName(), BorrowingFlattenedDto::setClientFirstName)
                .addMapping(src -> src.getClient().getLastName(), BorrowingFlattenedDto::setClientLastName)
                .addMapping(src -> src.getClient().getPatronymic(), BorrowingFlattenedDto::setClientPatronymic)
                .addMapping(src -> src.getClient().getBirthDate(), BorrowingFlattenedDto::setClientBirthDate);

        return modelMapper;
    }

}
