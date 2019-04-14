package internship.bookstore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import internship.bookstore.dto.AuthorDto;
import internship.bookstore.dto.BookDto;
import internship.bookstore.dto.BookRequestDto;
import internship.bookstore.entities.User;
import internship.bookstore.service.AuthorService;
import internship.bookstore.service.BookService;

@Controller
public class BookController {
	@Autowired
	BookService bookService;
	@Autowired
	AuthorService authorService;

	@GetMapping("/admin/bookHome")
	public ModelAndView goToBookhome(BookRequestDto request) {
		ModelAndView mv = new ModelAndView("admin/bookHome.html");
		List<BookDto> books = bookService.getAllBookBySearchTitle(request);
		int totalPages = (bookService.countBooks(request) + 4) / 5;
		mv.addObject("authors", authorService.getAllAuthors());
		mv.addObject("totalpages", totalPages);
		mv.addObject("books", books);
		mv.addObject("utilDto", request);
		return mv;
	}

	@PostMapping("/admin/bookHome")
	public ModelAndView searchInBookhome(@Valid BookRequestDto request) {
		ModelAndView mv = new ModelAndView("admin/bookHome.html");
		List<BookDto> books = bookService.getAllBookBySearchTitle(request);
		int totalPages = (bookService.countBooks(request) + 4) / 5;
		mv.addObject("authors", authorService.getAllAuthors());
		mv.addObject("books", books);
		mv.addObject("totalpages", totalPages);
		mv.addObject("utilDto", request);
		return mv;
	}

	@GetMapping("/book/AddBook")
	@Secured("ADMIN")
	public ModelAndView goToAddBookPage(BookDto bookDto, AuthorDto author) {
		ModelAndView mv = new ModelAndView("admin/addBook.html");
		mv.addObject("book", bookDto);
		mv.addObject("author", author);
		mv.addObject("authors", authorService.getAllAuthors());
		mv.addObject("addStatus", Boolean.TRUE);
		return mv;
	}

	@GetMapping("/book/edit/{isbn}")
	public ModelAndView goToEditBookPage(@PathVariable("isbn") Long isbn, AuthorDto author) {
		BookDto bookDto = bookService.getBookByIsbn(isbn);
		ModelAndView mv = new ModelAndView("admin/addBook.html");
		mv.addObject("book", bookDto);
		mv.addObject("author", author);
		mv.addObject("authors", authorService.getAllAuthors());
		mv.addObject("addStatus", Boolean.FALSE);
		return mv;
	}

	@GetMapping("book/delete/{isbn}")
	public ModelAndView delete(@PathVariable("isbn") Long isbn) {
		bookService.deleteBook(bookService.getBookByIsbn(isbn));
		return new ModelAndView("redirect:/admin/bookHome");
	}

	@PostMapping("/book")
	public ModelAndView addBook(@Valid BookDto bookDto, BindingResult result, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		bookDto.setUser(user);
		if (result.hasErrors() || !bookService.addBook(bookDto)) {
			return new ModelAndView("redirect:/book/AddBook");
		}
		return new ModelAndView("redirect:/admin/bookHome");
	}

	@PostMapping("/book/edit")
	public ModelAndView editBook(@Valid BookDto bookDto, BindingResult result, Model model,
			HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		bookDto.setUser(user);
		if (result.hasErrors()) {
			return new ModelAndView("redirect:/book/edit/{isbn}");
		}
		bookService.editBook(bookDto);
		return new ModelAndView("redirect:/admin/bookHome");
	}

}
