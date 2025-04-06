package org.example.dynamika.assignment.web.advice;

import lombok.extern.slf4j.Slf4j;
import org.example.dynamika.assignment.commons.ConstraintException;
import org.example.dynamika.assignment.commons.NotFoundException;
import org.example.dynamika.assignment.commons.ValidationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public ModelAndView handle(Exception e) {
        log.info(e.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("message", e);
        return modelAndView;
    }

    @ExceptionHandler(ValidationException.class)
    public ModelAndView handle(ValidationException e) {
        log.info(e.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("message", "Bad Request! " + e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(ConstraintException.class)
    public ModelAndView handle(ConstraintException e) {
        log.info(e.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("message", "Constraint check failed! " + e.getMessage());
        return modelAndView;
    }

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView handle(NotFoundException e) {
        log.info(e.getMessage());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("error");
        modelAndView.addObject("message", "Not Found! " + e.getMessage());
        return modelAndView;
    }

}
