package internship.bookstore.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import internship.bookstore.entities.Author;
import internship.bookstore.service.AuthorService;
import intership.bookstore.dto.AuthorDto;

@Controller
public class AuthorController {

	@Autowired
	AuthorService authorService;

	@GetMapping("/goToAddAuthorPage")
	public ModelAndView goToAddAuthorPage(Author author) {
		ModelAndView mv = new ModelAndView("admin/addAuthor.html");
		mv.addObject("author", author);
		return mv;
	}

	@GetMapping("/goToEditAuthorPage/{id}")
	public ModelAndView goToEditAuthorPage(@PathVariable("id") Long id) {
		AuthorDto authorDto = authorService.getAuthorById(id);
		ModelAndView mv = new ModelAndView("admin/editAuthor.html");
		mv.addObject("author", authorDto);
		return mv;
	}

	@GetMapping("/author/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {

		authorService.deleteAuthor(authorService.getAuthorById(id));
		ModelAndView mv = new ModelAndView("admin/authorHome.html");
		mv.addObject("authors", authorService.getAllAuthors());
		return mv;
	}

	@PostMapping("/author")
	public ModelAndView addAuthor(@Valid AuthorDto authorDto, BindingResult result) {
		if (result.hasErrors() || !authorService.addAuthor(authorDto)) {
			ModelAndView mv = new ModelAndView("admin/addAuthor.html");
			mv.addObject("author", authorDto);
			return mv;
		}
		ModelAndView mv = new ModelAndView("admin/authorHome.html");
		mv.addObject("authors", authorService.getAllAuthors());
		return mv;
	}

	@PutMapping("/author")
	public ModelAndView editAuthor( @Valid AuthorDto authorDto, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			ModelAndView mv = new ModelAndView("admin/editAuthor.html");
			mv.addObject("author", authorDto);
			return mv;
		}
		authorService.editAuthor(authorDto);
		ModelAndView mv = new ModelAndView("admin/authorHome.html");
		mv.addObject("authors", authorService.getAllAuthors());
		return mv;
	}

}
