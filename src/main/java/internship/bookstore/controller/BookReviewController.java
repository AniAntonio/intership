package internship.bookstore.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import internship.bookstore.converters.BookConverter;
import internship.bookstore.dto.BookDto;
import internship.bookstore.entities.BookReview;
import internship.bookstore.entities.User;
import internship.bookstore.service.AuthorService;
import internship.bookstore.service.BookReviewService;
import internship.bookstore.service.BookService;

@Controller
public class BookReviewController {

	@Autowired
	BookReviewService bookReviewService;

	@Autowired
	BookService bookService;

	@Autowired
	AuthorService authorService;

	@GetMapping("/book/bookReview/{isbn}")
	public ModelAndView goToBookReviewPage(@PathVariable("isbn") Long isbn, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		BookDto bookDto = bookService.getBookByIsbn(isbn);
		ModelAndView mv = new ModelAndView("admin/bookReview.html");
		mv.addObject("addReviewStatus", bookReviewService.checkIfUserHasDoneReview(isbn, user.getId()));
		mv.addObject("book", bookDto);
		mv.addObject("reviews", bookReviewService.getAllBookreviews(isbn));
		return mv;
	}

	@GetMapping("book/bookReview/create/{isbn}")
	public ModelAndView goToCreateReviewPage(@PathVariable("isbn") Long isbn, HttpServletRequest request,
			BookReview bookReview) {
		ModelAndView mv = new ModelAndView("admin/createReview.html");
		mv.addObject("review", bookReview);
		mv.addObject("isbn", isbn);
		return mv;
	}

	@PostMapping("/review/{isbn}")
	public ModelAndView createReview(@Valid BookReview bookReview, @PathVariable("isbn") Long isbn,
			HttpServletRequest request) {
		BookDto bookDto = bookService.getBookByIsbn(isbn);
		bookReview.setBook(BookConverter.toBookEntity(bookDto));
		User user = (User) request.getSession().getAttribute("user");
		bookReview.setUser(user);
		bookReviewService.addBookReview(bookReview);
		return new ModelAndView("redirect:/book/bookReview/" + isbn);
	}

}
