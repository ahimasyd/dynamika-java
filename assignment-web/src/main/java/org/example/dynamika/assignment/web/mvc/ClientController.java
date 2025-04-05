package org.example.dynamika.assignment.web.mvc;

import lombok.RequiredArgsConstructor;
import org.example.dynamika.assignment.dto.client.ClientDto;
import org.example.dynamika.assignment.dto.client.ClientOrderField;
import org.example.dynamika.assignment.dto.client.ClientSearchRequest;
import org.example.dynamika.assignment.dto.commons.OrderDirection;
import org.example.dynamika.assignment.dto.commons.OrderDto;
import org.example.dynamika.assignment.dto.commons.PagingRequestDto;
import org.example.dynamika.assignment.dto.commons.PagingResponseDto;
import org.example.dynamika.assignment.service.service.ClientService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
@RequestMapping("/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;


    @GetMapping(value = "", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView listView(@RequestParam(defaultValue = "0") int page,
                                    @RequestParam(defaultValue = "10") int size) {
        PagingRequestDto<ClientSearchRequest> request = PagingRequestDto.<ClientSearchRequest>builder()
                .page(page)
                .size(size)
                .filter(ClientSearchRequest.builder()
                        .orders(Collections.singletonList(
                                OrderDto.<ClientOrderField>builder()
                                        .field(ClientOrderField.ID)
                                        .direction(OrderDirection.ASC)
                                        .build()
                                )
                        )
                        .build()
                )
                .build();
        PagingResponseDto<ClientDto> response = clientService.search(request);

        ModelAndView model = new ModelAndView("client/clients");
        model.addObject("clients", response.getData());
        model.addObject("currentPage", response.getPage());
        model.addObject("pageSize", response.getSize());
        model.addObject("totalPages", Math.ceil((double) response.getTotal() / response.getSize()));
        model.addObject("saveRequest", new ClientDto());
        return model;
    }

    @PostMapping(value = "", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView create(@ModelAttribute("saveRequest") ClientDto saveRequest) {
        clientService.save(saveRequest);
        return new ModelAndView("redirect:/clients");
    }

    @GetMapping(value = "/edit/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView editView(@PathVariable("id") long id) {
        ClientDto client = clientService.getById(id);

        ModelAndView model = new ModelAndView("client/client-edit");
        model.addObject("saveRequest", client);
        return model;
    }

    @PostMapping(value = "/edit/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView edit(@ModelAttribute("saveRequest") ClientDto saveRequest) {
        clientService.save(saveRequest);
        return new ModelAndView("redirect:/clients");
    }

}
