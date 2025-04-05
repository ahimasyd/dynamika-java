package org.example.dynamika.assignment.web.mvc;

import lombok.RequiredArgsConstructor;
import org.example.dynamika.assignment.dto.book.BookDto;
import org.example.dynamika.assignment.dto.book.BookOrderField;
import org.example.dynamika.assignment.dto.book.BookSearchRequest;
import org.example.dynamika.assignment.dto.commons.OrderDirection;
import org.example.dynamika.assignment.dto.commons.OrderDto;
import org.example.dynamika.assignment.dto.commons.PagingRequestDto;
import org.example.dynamika.assignment.dto.commons.PagingResponseDto;
import org.example.dynamika.assignment.service.service.BookService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;


    @GetMapping(value = "", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView listBookView(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        PagingRequestDto<BookSearchRequest> request = PagingRequestDto.<BookSearchRequest>builder()
                .page(page)
                .size(size)
                .filter(BookSearchRequest.builder()
                        .orders(Collections.singletonList(
                                OrderDto.<BookOrderField>builder()
                                        .field(BookOrderField.ID)
                                        .direction(OrderDirection.ASC)
                                        .build()
                                )
                        )
                        .build()
                )
                .build();
        PagingResponseDto<BookDto> response = bookService.search(request);

        ModelAndView model = new ModelAndView("book/books");
        model.addObject("books", response.getData());
        model.addObject("currentPage", response.getPage());
        model.addObject("pageSize", response.getSize());
        model.addObject("totalPages", Math.ceil((double) response.getTotal() / response.getSize()));
        model.addObject("bookSaveRequest", new BookDto());
        return model;
    }

    @PostMapping(value = "", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView createBook(@ModelAttribute("bookSaveRequest") BookDto bookSaveRequest) {
        bookService.save(bookSaveRequest);
        return new ModelAndView("redirect:/books");
    }

    @GetMapping(value = "/edit/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView editBookView(@PathVariable("id") long id) {
        BookDto book = bookService.getById(id);

        ModelAndView model = new ModelAndView("book/book-edit");
        model.addObject("bookSaveRequest", book);
        return model;
    }

    @PostMapping(value = "/edit/{id}", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView editBook(@ModelAttribute("bookSaveRequest") BookDto bookSaveRequest) {
        bookService.save(bookSaveRequest);
        return new ModelAndView("redirect:/books");
    }

}
