package internship.bookstore.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import internship.bookstore.service.AuthorService;
import internship.bookstore.service.BookService;
import intership.bookstore.dto.AuthorDto;
import intership.bookstore.dto.BookDto;

@Controller
public class BookController {
	@Autowired
	BookService bookService;
	@Autowired
	AuthorService authorService;

	@GetMapping("/goToAddBookPage")
	public ModelAndView goToAddBookPage(BookDto bookDto, AuthorDto author) {
		ModelAndView mv = new ModelAndView("admin/addBook.html");
		mv.addObject("book", bookDto);
		mv.addObject("author", author);
		mv.addObject("authors", authorService.getAllAuthors());
		return mv;
	}

	@GetMapping("/goToEditBookPage/{isbn}")
	public ModelAndView goToEditBookPage(@PathVariable("isbn") Long isbn) {
		BookDto bookDto = bookService.getBookByIsbn(isbn);
		ModelAndView mv = new ModelAndView("admin/editBook.html");
		mv.addObject("book", bookDto);
		return mv;
	}

	@GetMapping("/deletebook/{isbn}")
	public ModelAndView delete(@PathVariable("isbn") Long isbn) {
		bookService.deleteBook(bookService.getBookByIsbn(isbn));
		return new ModelAndView("redirect: /admin/bookHome.html");
	}

	@PostMapping("/addBook")
	public ModelAndView addBook(@Valid BookDto bookDto, BindingResult result) {
		if (result.hasErrors() || !bookService.addBook(bookDto)) {

			ModelAndView mv = new ModelAndView("admin/addBook.html");
			mv.addObject("book", bookDto);
			return mv;
		}
		ModelAndView mv = new ModelAndView("admin/bookHome.html");
		mv.addObject("books", bookService.getAllBooks());
		return mv;
	}

	@PostMapping("/editBook/{isbn}")
	public ModelAndView editBook(@PathVariable("isbn") long isbn, @Valid BookDto bookDto, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("admin/editBook.html");
			mv.addObject("book", bookDto);
			return mv;
		}
		bookService.editBook(bookDto);
		ModelAndView mv = new ModelAndView("admin/bookHome.html");
		mv.addObject("books", bookService.getAllBooks());
		return mv;
	}

}
