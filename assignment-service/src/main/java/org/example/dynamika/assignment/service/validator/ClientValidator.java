package org.example.dynamika.assignment.service.validator;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.example.dynamika.assignment.commons.ValidationException;
import org.example.dynamika.assignment.dto.client.ClientDto;
import org.example.dynamika.assignment.dto.client.ClientSearchRequest;
import org.example.dynamika.assignment.dto.commons.PagingRequestDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ClientValidator {

    public void validateSearch(PagingRequestDto<ClientSearchRequest> request) {
        if (request == null) {
            throw new ValidationException("Request should be not null");
        }

        if (request.getFilter() == null || request.getPage() == null || request.getSize() == null) {
            throw new ValidationException("Request page, size and filter should be not null");
        }

        if (request.getPage() < 0) {
            throw new ValidationException("Request page should be at least 0");
        }

        if (request.getSize() < 1) {
            throw new ValidationException("Request page size should be at least 1");
        }
    }

    public void validateSave(ClientDto dto) {
        if (dto == null) {
            throw new ValidationException("Save request should not be null");
        }

        if (StringUtils.isBlank(dto.getLastName()) || StringUtils.isBlank(dto.getFirstName())) {
            throw new ValidationException("Client first and last name should be not empty");
        }

        if (dto.getBirthDate() == null || dto.getBirthDate().isAfter(LocalDate.now())) {
            throw new ValidationException("Client birth date can't be in the future");
        }
    }

}
