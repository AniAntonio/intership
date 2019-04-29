package internship.bookstore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import internship.bookstore.dto.BookDto;
import internship.bookstore.dto.BookRequestDto;
import internship.bookstore.entities.User;
import internship.bookstore.service.AuthorService;
import internship.bookstore.service.BookService;

@Controller
public class BookController {

	private static final String AUTHORS = "authors";
	private static final String ADD_BOOK_URL = "admin/addBook.html";
	private static final String BOOK_LIST_URL = "admin/bookList.html";
	private static final String ADD_STATUS = "addStatus";
	private static final String REDIRECT_TO_BOOK_LIST = "redirect:/admin/bookList";
	private static final String MESSAGE = "message";
	private static final String ALERT_CLASS = "alertClass";
	private static final String ALERT_SUCCESS = "alert alert-success";
	@Autowired
	BookService bookService;
	@Autowired
	AuthorService authorService;

	@GetMapping(value = "/admin/bookList")
	public ModelAndView goToBookList(BookRequestDto request) {
		ModelAndView mv = new ModelAndView(BOOK_LIST_URL);
		List<BookDto> books = bookService.getAllBooks(request);
		int totalPages = (bookService.countBooks(request) + 4) / 5;
		mv.addObject(AUTHORS, authorService.getAllAuthors());
		mv.addObject("totalpages", totalPages);
		mv.addObject("books", books);
		mv.addObject("utilDto", request);
		return mv;
	}

	@GetMapping("/book/add")
	public ModelAndView goToAddBookPage(BookDto bookDto) {
		ModelAndView mv = new ModelAndView(ADD_BOOK_URL);
		mv.addObject("book", bookDto);
		mv.addObject(AUTHORS, authorService.getAllAuthors());
		mv.addObject(ADD_STATUS, Boolean.TRUE);
		return mv;
	}

	@GetMapping("/book/edit/{isbn}")
	public ModelAndView goToEditBookPage(@PathVariable("isbn") Long isbn) {
		BookDto bookDto = bookService.getBookByIsbn(isbn);
		ModelAndView mv = new ModelAndView(ADD_BOOK_URL);
		mv.addObject("book", bookDto);
		mv.addObject(AUTHORS, authorService.getAllAuthors());
		mv.addObject(ADD_STATUS, Boolean.FALSE);
		return mv;
	}

	@GetMapping("book/delete/{isbn}")
	public ModelAndView delete(@PathVariable("isbn") Long isbn, RedirectAttributes redirectAttrs) {
		bookService.deleteBook(bookService.getBookByIsbn(isbn));
		redirectAttrs.addFlashAttribute(MESSAGE, "Book deleted successfully!");
		redirectAttrs.addFlashAttribute(ALERT_CLASS, ALERT_SUCCESS);
		return new ModelAndView(REDIRECT_TO_BOOK_LIST);
	}

	@PostMapping("/book")
	public ModelAndView saveBook(@ModelAttribute @Valid BookDto bookDto, HttpServletRequest request,
			RedirectAttributes redirectAttrs) {
		User user = (User) request.getSession().getAttribute("user");
		bookDto.setUser(user);
		ModelAndView mv = new ModelAndView(ADD_BOOK_URL);
		mv.addObject("book", bookDto);
		mv.addObject(MESSAGE, "Book with this title already exists!");
		mv.addObject(ALERT_CLASS, "alert-danger");
		if (bookDto.getIsbn() == null) {
			if (!bookService.saveBook(bookDto)) {
				mv.addObject(AUTHORS, authorService.getAllAuthors());
				mv.addObject(ADD_STATUS, Boolean.TRUE);
				return mv;
			}
			redirectAttrs.addFlashAttribute(MESSAGE, "Book added successfully!!");
			redirectAttrs.addFlashAttribute(ALERT_CLASS, ALERT_SUCCESS);
		} else if (!bookService.saveBook(bookDto)) {
			mv.addObject(AUTHORS, authorService.getAllAuthors());
			mv.addObject(ADD_STATUS, Boolean.FALSE);
			return mv;
		} else {
			redirectAttrs.addFlashAttribute(MESSAGE, "Book edited successfully!!");
			redirectAttrs.addFlashAttribute(ALERT_CLASS, ALERT_SUCCESS);
		}
		return new ModelAndView(REDIRECT_TO_BOOK_LIST);
	}
}
