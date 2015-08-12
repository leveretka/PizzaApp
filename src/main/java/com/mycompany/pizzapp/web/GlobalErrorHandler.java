package com.mycompany.pizzapp.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@ControllerAdvice
@EnableWebMvc
public class GlobalErrorHandler {

	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundPizzaException.class)
	public ModelAndView exceptionHandler(Exception exception, HttpServletRequest req) {
		ModelAndView model = new ModelAndView("error");
		model.addObject("ex", exception);
		model.addObject("url", req.getRequestURI());
		return model;
	}
	
}
