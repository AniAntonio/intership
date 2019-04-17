package internship.bookstore.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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

	@GetMapping(value="/admin/bookList")
	public ModelAndView goToBookList(BookRequestDto request) {
		ModelAndView mv = new ModelAndView("admin/bookList.html");
		List<BookDto> books = bookService.getAllBookBySearch(request);
		int totalPages = (bookService.countBooks(request) + 4) / 5;
		mv.addObject("authors", authorService.getAllAuthors());
		mv.addObject("totalpages", totalPages);
		mv.addObject("books", books);
		mv.addObject("utilDto", request);
		return mv;
	}

	@GetMapping("/book/AddBook")
	public ModelAndView goToAddBookPage(BookDto bookDto) {
		ModelAndView mv = new ModelAndView("admin/addBook.html");
		mv.addObject("book", bookDto);
		mv.addObject("authors", authorService.getAllAuthors());
		mv.addObject("addStatus", Boolean.TRUE);
		return mv;
	}

	@GetMapping("/book/edit/{isbn}")
	public ModelAndView goToEditBookPage(@PathVariable("isbn") Long isbn) {
		BookDto bookDto = bookService.getBookByIsbn(isbn);
		ModelAndView mv = new ModelAndView("admin/addBook.html");
		mv.addObject("book", bookDto);
		mv.addObject("authors", authorService.getAllAuthors());
		mv.addObject("addStatus", Boolean.FALSE);
		return mv;
	}

	@GetMapping("book/delete/{isbn}")
	public ModelAndView delete(@PathVariable("isbn") Long isbn) {
		bookService.deleteBook(bookService.getBookByIsbn(isbn));
		return new ModelAndView("redirect:/admin/bookList");
	}

	@PostMapping("/book")
	public ModelAndView addBook(@Valid BookDto bookDto, BindingResult result, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		bookDto.setUser(user);
		if ( !bookService.addBook(bookDto)) {
			ModelAndView mv = new ModelAndView("admin/addBook.html");
			mv.addObject("book", bookDto);
			mv.addObject("authors", authorService.getAllAuthors());
			mv.addObject("addStatus", Boolean.TRUE);
			mv.addObject("message",  "Book with this title already exists!");
			mv.addObject("alertClass", "alert-danger");
			return mv;
		}
		return new ModelAndView("redirect:/admin/bookList");
	}

	@PostMapping("/book/edit")
	public ModelAndView editBook(@Valid BookDto bookDto, BindingResult result, Model model, HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("user");
		bookDto.setUser(user);
		if ( !bookService.editBook(bookDto)) {
			ModelAndView mv = new ModelAndView("admin/addBook.html");
			mv.addObject("addStatus", Boolean.FALSE);
			mv.addObject("book", bookDto);
			mv.addObject("authors", authorService.getAllAuthors());
			mv.addObject("message",  "Book with this title already exists!");
			mv.addObject("alertClass", "alert-danger");
			return mv;
		}
		return new ModelAndView("redirect:/admin/bookList");
	}

}
