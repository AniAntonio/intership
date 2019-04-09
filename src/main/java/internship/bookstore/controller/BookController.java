package internship.bookstore.controller;

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

import internship.bookstore.entities.User;
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

	@GetMapping("/admin/bookHome")
	public ModelAndView goToBookhome() {
		ModelAndView mv = new ModelAndView("admin/bookHome.html");
		mv.addObject("books", bookService.getAllBooks());
		return mv;
	}

	@GetMapping("/book/AddBook")
	public ModelAndView goToAddBookPage(BookDto bookDto, AuthorDto author) {
		ModelAndView mv = new ModelAndView("admin/addBook.html");
		mv.addObject("book", bookDto);
		mv.addObject("author", author);
		mv.addObject("authors", authorService.getAllAuthors());
		return mv;
	}

	@GetMapping("/book/edit/{isbn}")
	public ModelAndView goToEditBookPage(@PathVariable("isbn") Long isbn) {
		BookDto bookDto = bookService.getBookByIsbn(isbn);
		ModelAndView mv = new ModelAndView("admin/editBook.html");
		mv.addObject("book", bookDto);
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
	public ModelAndView editBook(@Valid BookDto bookDto, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return new ModelAndView("redirect:/book/edit/{isbn}");
		}
		bookService.editBook(bookDto);
		return new ModelAndView("redirect:/admin/bookHome");
	}

}
