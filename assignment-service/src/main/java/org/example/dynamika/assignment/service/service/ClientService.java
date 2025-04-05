package org.example.dynamika.assignment.service.service;

import org.example.dynamika.assignment.dto.client.ClientDto;
import org.example.dynamika.assignment.dto.client.ClientSearchRequest;
import org.example.dynamika.assignment.dto.commons.PagingRequestDto;
import org.example.dynamika.assignment.dto.commons.PagingResponseDto;

public interface ClientService {

    ClientDto getById(Long id);

    PagingResponseDto<ClientDto> search(PagingRequestDto<ClientSearchRequest> request);

    ClientDto save(ClientDto saveRequest);

}
