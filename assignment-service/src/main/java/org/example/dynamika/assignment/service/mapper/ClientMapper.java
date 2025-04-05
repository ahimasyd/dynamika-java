package org.example.dynamika.assignment.service.mapper;

import lombok.RequiredArgsConstructor;
import org.example.dynamika.assignment.dto.client.ClientDto;
import org.example.dynamika.assignment.persistence.entity.Client;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientMapper {

    private final ModelMapper modelMapper;


    public ClientDto toDto(Client entity) {
        return modelMapper.map(entity, ClientDto.class);
    }

    public Client toEntity(ClientDto dto) {
        return modelMapper.map(dto, Client.class);
    }

}
