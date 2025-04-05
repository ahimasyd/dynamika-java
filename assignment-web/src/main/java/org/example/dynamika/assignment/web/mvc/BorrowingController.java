package org.example.dynamika.assignment.web.mvc;

import lombok.RequiredArgsConstructor;
import org.example.dynamika.assignment.dto.borrowing.BorrowRequest;
import org.example.dynamika.assignment.service.service.BorrowingService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/borrowings")
@RequiredArgsConstructor
public class BorrowingController {

    private final BorrowingService borrowingService;


    @GetMapping(value = "", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView borrowView() {
        ModelAndView model = new ModelAndView("borrowing/borrow");
        model.addObject("saveRequest", new BorrowRequest());
        return model;
    }

    @PostMapping(value = "", produces = MediaType.TEXT_HTML_VALUE)
    public ModelAndView borrow(@ModelAttribute("saveRequest") BorrowRequest request) {
        borrowingService.borrow(request);
        return new ModelAndView("redirect:/");
    }


}
