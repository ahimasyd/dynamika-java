package org.example.dynamika.assignment.service.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.dynamika.assignment.commons.NotFoundException;
import org.example.dynamika.assignment.dto.client.ClientDto;
import org.example.dynamika.assignment.dto.client.ClientSearchRequest;
import org.example.dynamika.assignment.dto.commons.PagingRequestDto;
import org.example.dynamika.assignment.dto.commons.PagingResponseDto;
import org.example.dynamika.assignment.persistence.criteria.ClientCriteria;
import org.example.dynamika.assignment.persistence.entity.Client;
import org.example.dynamika.assignment.persistence.repository.ClientRepository;
import org.example.dynamika.assignment.service.mapper.ClientMapper;
import org.example.dynamika.assignment.service.service.ClientService;
import org.example.dynamika.assignment.service.validator.ClientValidator;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final ClientValidator clientValidator;


    @Transactional(readOnly = true)
    @Override
    public ClientDto getById(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Client not found by id"));
        return clientMapper.toDto(client);
    }

    @Transactional(readOnly = true)
    @Override
    public PagingResponseDto<ClientDto> search(PagingRequestDto<ClientSearchRequest> request) {
        clientValidator.validateSearch(request);

        Page<Client> resultPage = clientRepository.findAll(
                ClientCriteria.search(request.getFilter()),
                PageRequest.of(request.getPage(), request.getSize())
        );

        List<ClientDto> dtos = resultPage.stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
        return PagingResponseDto.<ClientDto>builder()
                .page(resultPage.getNumber())
                .size(resultPage.getSize())
                .total(resultPage.getTotalElements())
                .data(dtos)
                .build();
    }

    @Transactional
    @Override
    public ClientDto save(ClientDto saveRequest) {
        clientValidator.validateSave(saveRequest);

        Client client;
        if (saveRequest.getId() == null) {
            client = clientMapper.toEntity(saveRequest);
        } else {
            client = clientRepository.findById(saveRequest.getId())
                    .orElseThrow(() -> new NotFoundException("Client not found by id"));

            client.setFirstName(saveRequest.getFirstName());
            client.setLastName(saveRequest.getLastName());
            client.setPatronymic(saveRequest.getPatronymic());
            client.setBirthDate(saveRequest.getBirthDate());
        }

        client = clientRepository.save(client);
        return clientMapper.toDto(client);
    }

}
