package internship.bookstore.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import internship.bookstore.exceptions.CustomException;

@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ModelAndView handleException(CustomException ex, RedirectAttributes redAttr) {
		redAttr.addFlashAttribute("message", ex.getMessage());
		return new ModelAndView("redirect:/errorPage");
	}


}
